package com.team.orm.service;

import com.team.orm.entity.UserPO;
import com.team.orm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserPO> findAll() {
        return userRepository.findAll();
    }
}
