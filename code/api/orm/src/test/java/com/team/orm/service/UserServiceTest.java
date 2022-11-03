package com.team.orm.service;

import com.team.orm.entity.UserPO;
import com.team.orm.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JpaTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testSelect() {
        List<UserPO> users = userRepository.findAll();
        System.out.println("a");
    }
}