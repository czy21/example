package com.team.fileresolve.receiver;

import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.team.application.automap.RowAutoMap;
import com.team.application.config.QueueConfig;
import com.team.application.model.RowModel;
import com.team.application.model.vo.MaterialVO;
import com.team.application.service.MaterialService;
import com.team.domain.entity.MaterialEntity;
import com.team.domain.mapper.MaterialMapper;
import com.team.domain.mapper.RepositoryMapper;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import com.team.fileresolve.listener.FileListener;
import com.team.infrastructure.datasource.DynamicDataSourceContext;
import com.team.infrastructure.oss.OSSClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class RabbitReceiver {

    @Autowired
    MaterialMapper materialMapper;
    @Autowired
    RabbitTemplate rt;
    @Autowired
    FileColumnMappingRepository fmr;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    RowAutoMap rowAutoMap;
    @Autowired
    RepositoryMapper repositoryMapper;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Autowired
    OSSClient ossClient;
    @Autowired
    MaterialService materialService;

    @RabbitListener(queues = QueueConfig.SPI_FILE_TOPIC, concurrency = "1", ackMode = "MANUAL")
    public void file(MaterialVO materialVO, Message message, Channel channel) throws Exception {
        MaterialEntity me = materialMapper.selectById(materialVO.getUid());
        InputStream inputStream = materialService.getStreamBy(me);
        EasyExcel.read(inputStream, new FileListener(rt, fmr, rowAutoMap, me, materialVO)).sheet().doRead();
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory batchListenerFactory(ConnectionFactory connectionFactory, @Qualifier("jsonMessageConverter") MessageConverter messageConverter, @Value("${spi.batch-size}") Integer batchSize, @Value("${spi.consumer-size}") Integer consumerSize) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setBatchListener(true);
        factory.setConsumerBatchEnabled(true);
        factory.setBatchSize(batchSize);
        factory.setConcurrentConsumers(consumerSize);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    @SneakyThrows
    @RabbitListener(queues = QueueConfig.SPI_DATA_TOPIC, containerFactory = "batchListenerFactory")
    public void rowData(List<RowModel> rows) {
        var gRows = rows.stream().collect(Collectors.groupingBy(RowModel::getBusinessType, Collectors.toList()));
        gRows.forEach((k, v) -> {
            var tableMeta = v.get(0);
            List<MutablePair<String, String>> columns = Stream.concat(Stream.of(MutablePair.of("id", "id")), tableMeta.getData().values().stream().map(t -> MutablePair.of(t.getKey(), t.getColumn()))).collect(Collectors.toList());
            DynamicDataSourceContext.put("ds1");
            mybatisInsert(v, tableMeta, columns);
        });
    }

    private void mybatisInsert(List<RowModel> rows, RowModel tableMeta, List<MutablePair<String, String>> columns) {
        SQL sql = new SQL();
        sql.INSERT_INTO(tableMeta.getTableName());
        sql.INTO_COLUMNS(columns.stream().map(Pair::getValue).collect(Collectors.toList()).toArray(new String[]{}));
        sql.INTO_VALUES(columns.stream().map(t -> t.getValue().equals("id") ? "replace(uuid(),'-','')" : StringUtils.join(List.of("#{", t.getKey(), "}"), "")).collect(Collectors.toList()).toArray(new String[]{}));
        String sqlStatement = sql.toString();
        var sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, TransactionIsolationLevel.NONE);
        rows.forEach(t -> {
            Map<String, Object> param = t.getData().entrySet().stream().collect(HashMap::new, (m, v) -> m.put(v.getKey(), v.getValue().getValue()), Map::putAll);
            param.put("sql", sqlStatement);
            sqlSession.insert("com.team.domain.mapper.RepositoryMapper.insert", param);
        });
        sqlSession.commit();
        sqlSession.clearCache();
        sqlSession.close();
    }

}
