package com.team.service;

import com.alibaba.fastjson.JSONObject;
import com.team.core.universal.MybatisBaseService;
import com.team.domain.entity.PermissionEntity;
import com.team.domain.entity.RoleEntity;
import com.team.domain.entity.UserEntity;
import com.team.entity.dto.LoginDTO;
import com.team.entity.dto.PageDTO;
import com.team.entity.dto.UserDTO;
import com.team.model.SearchUserModel;

import java.util.List;

/**
 * @Description User 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface UserService extends MybatisBaseService<UserEntity> {

    UserDTO insertDefaultPwd(UserDTO user);

    UserDTO editUser(UserDTO dto);

    Boolean modifiedUser(UserDTO dto);

    PageDTO<UserDTO> getUserPageListBy(SearchUserModel search);

    List<RoleEntity> getRolesByLoginName(String loginName);

    List<PermissionEntity> getFunctionsByRole(List<String> roleIds);

    JSONObject login(LoginDTO dto);

    JSONObject register(LoginDTO dto);

    List<PermissionEntity> getFunctionsByUser(String userId);

}
