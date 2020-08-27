package com.team.domain.repository.neo4j;

import com.team.domain.node.CompanyNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyRepositoryTest {
    @SpringBootApplication(scanBasePackages = "com.team.domain")
    static class ExampleConfig {
    }

    @Autowired
    CompanyRepository companyRepository;

    @Test
    public void add() {
        CompanyNode company1 = new CompanyNode("上海公司1");
        companyRepository.save(company1);
    }
}