package com.team.application.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.team.domain.entity.UserEntity;
import com.team.domain.mapper.UserMapper;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQueryResolver implements GraphQLQueryResolver {


    @Autowired
    UserMapper userMapper;

    public List<UserEntity> findAllUser(Integer pageIndex,Integer pageSize) {

        return userMapper.selectList(null);
    }

}
