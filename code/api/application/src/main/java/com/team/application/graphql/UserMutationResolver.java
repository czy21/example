package com.team.application.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.team.domain.entity.UserEntity;
import com.team.domain.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMutationResolver implements GraphQLMutationResolver {

    private final UserMapper userMapper;

    public UserMutationResolver(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<UserEntity> updateUser(UserEntity filter) {
        return List.of(filter);
    }

}
