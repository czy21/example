package com.team.application;

import com.team.application.util.DateTimeUtil;
import com.team.domain.model.CompanyEntity;
import com.team.domain.model.DepartmentEntity;
import com.team.domain.repository.jpa.CompanyRepository;
import com.team.domain.repository.jpa.DepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JpaTest {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void findAll1() {
        List<DepartmentEntity> entities = departmentRepository.findAll();
        System.out.println("aa");
    }

    @Test
    public void add1() {
        List<DepartmentEntity> entities = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            DepartmentEntity entity = new DepartmentEntity();
            entity.setId(UUID.randomUUID().toString());
            entity.setName("ceshi");
            CompanyEntity companyEntity = new CompanyEntity();
            companyEntity.setId("03aec595-24c4-11ea-9a7c-0242ac150002");
            entities.add(entity);
            entity.setCompany(companyEntity);
        }
        System.out.println(DateTimeUtil.toTimeStamp(LocalDateTime.now()));
//        entities.forEach(s-> departmentRepository.save(s));
        List<DepartmentEntity> saveEntity = departmentRepository.saveAll(entities);

//        jdbcTemplate.batchUpdate("insert into ent_sys_department(id,name,company_id) values (?,?,?)", new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setString(1,entities.get(i).getId());
//                ps.setString(2,entities.get(i).getName());
//                ps.setString(3,entities.get(i).getCompany().getId());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return entities.size();
//            }
//        });


        System.out.println(DateTimeUtil.toTimeStamp(LocalDateTime.now()));
        System.out.println("aaa");
    }

}
