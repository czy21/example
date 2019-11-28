package com.team.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.team.domain.entity.UserEntity;
import com.team.domain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResolver implements GraphQLQueryResolver {
    @Autowired
    private UserMapper userRepository;

    public List<UserEntity> findAllUsers() {
        return userRepository.selectList(null);
    }
}
