package com.team.application.service;

import com.team.application.core.universal.MybatisBaseService;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.UserDTO;
import com.team.application.model.vo.SearchUserModel;
import com.team.domain.entity.PermissionEntity;
import com.team.domain.entity.RoleEntity;
import com.team.domain.entity.UserEntity;

import java.util.List;

public interface UserService extends MybatisBaseService<UserEntity> {

    UserDTO insertDefaultPwd(UserDTO user);

    UserDTO editUser(UserDTO dto);

    Boolean modifiedUser(UserDTO dto);

    PageDTO<UserDTO> getUserPageListBy(SearchUserModel search);

    List<RoleEntity> getRolesByLoginName(String loginName);

    List<PermissionEntity> getFunctionsByRole(List<String> roleIds);
//
//    JSONObject login(LoginDTO dto);
//
//    JSONObject register(LoginDTO dto);

    List<PermissionEntity> getFunctionsByUser(String userId);

}
