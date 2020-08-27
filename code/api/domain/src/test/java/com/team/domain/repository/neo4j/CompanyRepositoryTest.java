package com.team.domain.repository.neo4j;

import com.team.domain.StartupApplicationTest;
import com.team.domain.node.CompanyNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartupApplicationTest.class)
public class CompanyRepositoryTest {

    @Autowired
    CompanyNeo4jRepository companyRepository;

    @Test
    public void add() {
        CompanyNode company1 = new CompanyNode("上海公司1");
        companyRepository.save(company1);
    }
}