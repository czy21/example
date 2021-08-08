package com.team.application.service.impl;

import com.team.application.service.PersistService;
import com.team.application.service.SaleService;
import com.team.application.service.TableMetadataService;
import com.team.domain.entity.SaleEntity;
import com.team.domain.mapper.SaleMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    TableMetadataService tableMetadataService;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public void migrateToHBase(PersistService persistService) {
        List<SaleEntity> sales = new ArrayList<>();
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            SaleMapper saleMapper = sqlSession.getMapper(SaleMapper.class);
            Cursor<SaleEntity> cursor = saleMapper.selectAllForCursor();
            for (SaleEntity t : cursor) {
                sales.add(t);
                if (sales.size() >= 100000) {
                    persistService.persist(sales);
                    sales.clear();
                }
            }
            cursor.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (sales.size() > 0) {
            persistService.persist(sales);
        }
    }


}
