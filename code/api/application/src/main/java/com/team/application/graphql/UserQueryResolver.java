package com.team.application.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.domain.entity.UserEntity;
import com.team.domain.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQueryResolver implements GraphQLQueryResolver {

    private final UserMapper userMapper;
    private final ObjectMapper objectMapper;

    public UserQueryResolver(UserMapper userMapper, ObjectMapper objectMapper) {
        this.userMapper = userMapper;
        this.objectMapper = objectMapper;
    }

    public List<UserEntity> findAllUser(String filter) {

        return userMapper.selectList(null);
    }

}
