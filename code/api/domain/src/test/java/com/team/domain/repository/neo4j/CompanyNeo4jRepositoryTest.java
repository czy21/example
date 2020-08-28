package com.team.domain.repository.neo4j;

import com.team.domain.StartupApplicationTest;
import com.team.domain.node.CompanyNode;
import com.team.domain.node.DepartmentNode;
import com.team.domain.node.EmployeeNode;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartupApplicationTest.class)
public class CompanyNeo4jRepositoryTest {

    @Autowired
    CompanyNeo4jRepository companyRepository;
    @Autowired
    DepartmentNeo4jRepository departmentNeo4jRepository;
    public static String companyName1 = "上海公司1";
    public static String departmentName1 = "部门1";
    public static String departmentName2 = "部门2";
    public static String userName1 = "用户1";

    @Test
    public void add() {
        CompanyNode company1 = new CompanyNode(companyName1);
        DepartmentNode department1 = new DepartmentNode(departmentName1);
        DepartmentNode department2 = new DepartmentNode(departmentName2);
        List<DepartmentNode> departmentNodes = new ArrayList<>();
        departmentNodes.add(department1);
        departmentNodes.add(department2);
        company1.setDepartments(departmentNodes);

        List<EmployeeNode> users = new ArrayList<>();
        EmployeeNode user1 = new EmployeeNode(userName1);
        users.add(user1);
        department1.setEmployees(users);
        companyRepository.save(company1);
    }

    @Test
    public void batchAdd() {
        CompanyNode company2 = new CompanyNode(companyName1);
        List<DepartmentNode> departments = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            DepartmentNode dept = new DepartmentNode(StringUtils.join("部门", i));
            dept.setEmployees(List.of(new EmployeeNode(StringUtils.join("员工", i))));
            departments.add(dept);
        }
        company2.setDepartments(departments);
        companyRepository.save(company2);

    }

    @Test
    public void selectCompany() {
        CompanyNode company1 = companyRepository.findByName(companyName1, 2);
        assertThat(company1).isNotNull();
    }

    @Test
    public void selectDepartment() {
        DepartmentNode dept1 = departmentNeo4jRepository.findByName(departmentName1, 1);
        assertThat(dept1).isNotNull();
    }
}