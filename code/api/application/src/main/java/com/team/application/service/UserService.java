package com.team.application.service;

import com.team.application.base.MybatisBaseService;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.UserDTO;
import com.team.application.model.page.PageInput;
import com.team.application.model.vo.BaseImportVO;
import com.team.application.model.vo.UserVO;
import com.team.domain.entity.PermissionEntity;
import com.team.domain.entity.RoleEntity;
import com.team.domain.entity.UserEntity;

import java.io.IOException;
import java.util.List;

public interface UserService extends MybatisBaseService<UserEntity> {

    UserDTO insertDefaultPwd(UserDTO user);

    UserDTO editUser(UserDTO dto);

    Boolean modifiedUser(UserDTO dto);

    PageDTO<UserDTO> getUserPageListBy(PageInput page, UserVO user);

    List<RoleEntity> getRolesByLoginName(String loginName);

    List<PermissionEntity> getFunctionsByRole(List<String> roleIds);
//
//    JSONObject login(LoginDTO dto);
//
//    JSONObject register(LoginDTO dto);

    List<PermissionEntity> getFunctionsByUser(String userId);

    void userImport(BaseImportVO importVO) throws IOException;

}
