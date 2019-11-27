package com.team.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.team.entity.mybatis.system.User;
import com.team.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResolver implements GraphQLQueryResolver {
    @Autowired
    private UserMapper userRepository;

    public List<User> findAllUsers() {
        return userRepository.selectList(null);
    }
}
