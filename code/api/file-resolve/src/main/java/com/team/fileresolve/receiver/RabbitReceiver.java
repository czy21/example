package com.team.fileresolve.receiver;

import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.team.application.automap.RowAutoMap;
import com.team.application.config.QueueConfig;
import com.team.application.model.RowModel;
import com.team.application.model.vo.MaterialVO;
import com.team.domain.entity.MaterialEntity;
import com.team.domain.mapper.MaterialMapper;
import com.team.domain.mapper.RepositoryMapper;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import com.team.fileresolve.listener.FileListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
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

    @RabbitListener(queues = QueueConfig.SPI_FILE_TOPIC, concurrency = "1", ackMode = "MANUAL")
    public void file(MaterialVO materialVO, Message message, Channel channel) throws IOException {
        MaterialEntity me = materialMapper.selectById(materialVO.getUid());
        File f = Paths.get(me.getMaterialTarget().getRootUrl(), me.getMaterialTarget().getRootPath(), me.getPath()).toFile();
        EasyExcel.read(f, new FileListener(rt, fmr, rowAutoMap, me)).sheet().doRead();
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = QueueConfig.SPI_DATA_TOPIC, concurrency = "5", ackMode = "MANUAL")
    public void rowData(RowModel row, Message message, Channel channel) throws IOException {
        SQL sql = new SQL();
        sql.INSERT_INTO(row.getTableName());
        var data = row.getData().values().stream().filter(t -> t.getValue() != null).collect(Collectors.toList());
        String columns = Stream.concat(
                Stream.of("id"),
                data.stream().map(RowModel.ColModel::getColumn)).collect(Collectors.joining(",")
        );
        String values = Stream.concat(
                Stream.of(UUID.randomUUID().toString().replace("-", "")),
                data.stream().map(t -> t.getValue().toString())).map(t -> StringUtils.wrap(t, "'")).collect(Collectors.joining(",")
        );
        sql.VALUES(columns, values);
        repositoryMapper.insert(Map.of("sql", sql));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
