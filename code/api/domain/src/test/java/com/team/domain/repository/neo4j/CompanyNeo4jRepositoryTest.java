package com.team.domain.repository.neo4j;

import com.team.domain.StartupApplicationTest;
import com.team.domain.node.CompanyNode;
import com.team.domain.node.DepartmentNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartupApplicationTest.class)
public class CompanyNeo4jRepositoryTest {

    @Autowired
    CompanyNeo4jRepository companyRepository;
    @Autowired
    DepartmentNeo4jRepository departmentNeo4jRepository;

    @Test
    public void add() {
        CompanyNode company1 = new CompanyNode("上海公司1");
        DepartmentNode department1 = new DepartmentNode("部门1");
        DepartmentNode department2 = new DepartmentNode("部门2");
        List<DepartmentNode> departmentNodes = new ArrayList<>();
        departmentNodes.add(department1);
        departmentNodes.add(department2);
        company1.setDepartments(departmentNodes);
        companyRepository.save(company1);
    }

    @Test
    public void selectCompany() {
        CompanyNode node = companyRepository.findById((long) 106).orElse(null);
        System.out.println("aaa");
    }

    @Test
    public void selectDepartment() {
        DepartmentNode dept1 = departmentNeo4jRepository.findById(107L).orElse(null);
        System.out.println("aaa");
    }
}