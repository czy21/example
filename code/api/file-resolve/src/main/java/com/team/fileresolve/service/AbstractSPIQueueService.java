package com.team.fileresolve.service;

import com.alibaba.excel.EasyExcel;
import com.team.application.automap.RowAutoMap;
import com.team.application.model.RowModel;
import com.team.application.model.vo.MaterialVO;
import com.team.application.service.MaterialService;
import com.team.domain.entity.MaterialEntity;
import com.team.domain.mapper.MaterialMapper;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import com.team.fileresolve.listener.FileListener;
import com.team.infrastructure.datasource.DynamicDataSourceContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.pulsar.client.api.PulsarClientException;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractSPIQueueService implements SPIQueueService {

    RowAutoMap rowAutoMap;
    FileColumnMappingRepository fileColumnMappingRepository;
    MaterialMapper materialMapper;
    MaterialService materialService;
    SqlSessionFactory sqlSessionFactory;

    public AbstractSPIQueueService(RowAutoMap rowAutoMap,
                                   FileColumnMappingRepository fileColumnMappingRepository,
                                   MaterialMapper materialMapper,
                                   MaterialService materialService,
                                   SqlSessionFactory sqlSessionFactory) {
        this.rowAutoMap = rowAutoMap;
        this.fileColumnMappingRepository = fileColumnMappingRepository;
        this.materialMapper = materialMapper;
        this.materialService = materialService;
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public abstract void produceRow(String topic, RowModel rowData) throws Exception;

    @Override
    public void resolveFile(MaterialVO materialVO) throws Exception {
        MaterialEntity me = materialMapper.selectById(materialVO.getUid());
        InputStream inputStream = materialService.getStreamBy(me);
        EasyExcel.read(inputStream, new FileListener(this, fileColumnMappingRepository, rowAutoMap, me, materialVO)).sheet().doRead();
    }

    @Override
    public void consumeRow(List<RowModel> rows) {
        var gRows = rows.stream().collect(Collectors.groupingBy(RowModel::getBusinessType, Collectors.toList()));
        gRows.forEach((k, v) -> {
            var tableMeta = v.get(0);
            List<MutablePair<String, String>> columns = Stream.concat(Stream.of(MutablePair.of("id", "id")), tableMeta.getData().values().stream().map(t -> MutablePair.of(t.getKey(), t.getColumn()))).collect(Collectors.toList());
            mybatisInsert(v, tableMeta, columns);
        });
    }

    private void mybatisInsert(List<RowModel> rows, RowModel tableMeta, List<MutablePair<String, String>> columns) {
        SQL sql = new SQL();
        sql.INSERT_INTO(tableMeta.getTableName());
        sql.INTO_COLUMNS(columns.stream().map(Pair::getValue).collect(Collectors.toList()).toArray(new String[]{}));
        sql.INTO_VALUES(columns.stream().map(t -> t.getValue().equals("id") ? "UUID_SHORT()" : StringUtils.join(List.of("#{", t.getKey(), "}"), "")).collect(Collectors.toList()).toArray(new String[]{}));
        String sqlStatement = sql.toString();
        try (var sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, TransactionIsolationLevel.NONE)) {
            rows.forEach(t -> {
                Map<String, Object> param = t.getData().entrySet().stream().collect(HashMap::new, (m, v) -> m.put(v.getKey(), v.getValue().getValue()), Map::putAll);
                param.put("sql", sqlStatement);
                sqlSession.insert("com.team.domain.mapper.RepositoryMapper.insert", param);
            });
            sqlSession.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
