package com.team.application.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.team.application.model.automap.UserAutoMap;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.UserDTO;
import com.team.application.model.vo.PageUserInput;
import com.team.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMutationResolver implements GraphQLMutationResolver, GraphQLQueryResolver {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAutoMap userAutoMap;

    public PageDTO<UserDTO> searchUser(PageUserInput input) {
        return userService.getUserPageListBy(input.getPage(), input.getFilter());
    }

    public UserDTO userDetail(String id) {
        return userAutoMap.mapToDto(userService.selectOneById(null));
    }

    public List<UserDTO> findAllUser() {
        return userAutoMap.mapToDtos(userService.selectAll(null));
    }


}
