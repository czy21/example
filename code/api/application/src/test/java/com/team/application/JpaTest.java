package com.team.application;

import com.team.domain.model.CompanyEntity;
import com.team.domain.model.DepartmentEntity;
import com.team.domain.repository.jpa.CompanyRepository;
import com.team.domain.repository.jpa.DepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JpaTest {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    public void findAll1() {
        List<DepartmentEntity> entities = departmentRepository.findAll();
        System.out.println("aa");
    }

    @Test
    public void add1() {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setName("ceshi");
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId("03aec595-24c4-11ea-9a7c-0242ac150002");

        entity.setCompany(companyEntity);

        DepartmentEntity saveEntity = departmentRepository.saveAndFlush(entity);

        System.out.println("aaa");


    }

}
