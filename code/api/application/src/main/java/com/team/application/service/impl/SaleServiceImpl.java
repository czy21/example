package com.team.application.service.impl;

import com.team.application.service.PersistService;
import com.team.application.service.SaleService;
import com.team.application.service.TableMetadataService;
import com.team.domain.entity.SaleEntity;
import com.team.domain.mapper.SaleMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    TableMetadataService tableMetadataService;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Value("${migrate.batch-number}")
    int migrateBatchNumber;

    @Data
    public static class MigrateContext {
        private int sequence;
        private String batchId;

        public MigrateContext() {
        }

        public MigrateContext(int sequence, String batchId) {
            this.sequence = sequence;
            this.batchId = batchId;
        }

        public static MigrateContext of(int sequence, String batchId) {
            return new MigrateContext(sequence, batchId);
        }
    }

    @Override
    public void migrateToPersist(PersistService persistService) {
        String batchId = UUID.randomUUID().toString().replace("-", "");
        int seq = 0;
        List<SaleEntity> sales = new ArrayList<>();
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            SaleMapper saleMapper = sqlSession.getMapper(SaleMapper.class);
            Cursor<SaleEntity> cursor = saleMapper.selectAllForCursor();
            for (SaleEntity t : cursor) {
                sales.add(t);
                if (sales.size() >= migrateBatchNumber) {
                    seq++;
                    persistService.persist(sales, MigrateContext.of(seq, batchId));
                    sales.clear();
                }
            }
            cursor.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (sales.size() > 0) {
            seq++;
            persistService.persist(sales, MigrateContext.of(seq, batchId));
        }
    }


}
