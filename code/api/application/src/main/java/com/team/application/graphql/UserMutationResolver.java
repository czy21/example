package com.team.application.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.vo.PageUserInput;
import com.team.cooperated.exception.BusinessErrorCode;
import com.team.cooperated.exception.BusinessException;
import com.team.domain.entity.UserEntity;
import com.team.domain.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserMutationResolver implements GraphQLMutationResolver, GraphQLQueryResolver {

    private final UserMapper userMapper;

    public UserMutationResolver(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public PageDTO<UserEntity> searchUser(PageUserInput input) {
//        UserEntity entity = new UserEntity();
//        entity.setId("1");
//        entity.setUserName("nishishei");
//        PageDTO<UserEntity> dto = new PageDTO<>();
//        dto.setPage(input.getPage());
//        dto.setList(List.of(entity));
//        return dto;

        throw new BusinessException(BusinessErrorCode.EXIST_USER);
    }

    public UserEntity userDetail(String id) {
        return userMapper.selectList(null).get(0);
    }

    public List<UserEntity> findAllUser() {
        return userMapper.selectList(null);
    }


}
