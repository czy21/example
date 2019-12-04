package com.team.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.application.base.MybatisBaseServiceImpl;
import com.team.application.model.automap.UserAutoMap;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.UserDTO;
import com.team.application.model.page.PageInput;
import com.team.application.model.vo.UserVO;
import com.team.application.service.UserService;
import com.team.cooperated.exception.BusinessErrorCode;
import com.team.cooperated.exception.BusinessException;
import com.team.domain.entity.PermissionEntity;
import com.team.domain.entity.RoleEntity;
import com.team.domain.entity.UserEntity;
import com.team.domain.mapper.PermissionMapper;
import com.team.domain.mapper.RoleMapper;
import com.team.domain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends MybatisBaseServiceImpl<UserMapper, UserEntity> implements UserService {

    @Resource
    private UserAutoMap userMap;
    @Autowired
    private RoleMapper roleRepository;
    @Autowired
    private PermissionMapper functionRepository;

    @Override
    public UserDTO insertDefaultPwd(UserDTO dto) {
        if (StringUtils.isEmpty(dto.getUserName())) {
            throw new BusinessException(BusinessErrorCode.EXIST_NAME, "用户姓名不能为空");
        }
        if (super.selectOne(UserEntity::getLoginName, dto.getLoginName()) != null) {
            throw new BusinessException(BusinessErrorCode.EXIST_USER, "用户账号已存在");
        }
        UserEntity user = userMap.mapToEntity(dto);
        user.setPassword("123456");
        return userMap.mapToDto(super.insertAndGet(user));
    }

    @Override
    public UserDTO editUser(UserDTO dto) {
        if (StringUtils.isEmpty(dto.getId())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "用户Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getUserName())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_NAME, "用户姓名不能为空");
        }
        return userMap.mapToDto(super.updateAndGet(userMap.mapToEntity(dto)));
    }

    @Override
    public Boolean modifiedUser(UserDTO dto) {
        if (StringUtils.isEmpty(dto.getId())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "用户Id不能为空");
        }

        return null;
    }

    @Override
    public PageDTO<UserDTO> getUserPageListBy(PageInput page, UserVO user) {
        return userMap.mapToPageDto(super.selectAll(page.getPageIndex(), page.getPageSize(), (QueryWrapper<UserEntity>) null));
    }

    @Override
    public List<RoleEntity> getRolesByLoginName(String loginName) {
        return roleRepository.selectAllByLoginName(loginName);
    }

    @Override
    public List<PermissionEntity> getFunctionsByRole(List<String> roleIds) {
        return functionRepository.selectAllByRoleIds(roleIds);
    }

//    @Override
//    public JSONObject login(LoginDTO dto) {
//        UserEntity user = super.selectOne(UserEntity::getLoginName, dto.getLoginName());
//        if (ObjectUtils.isEmpty(user)) {
//            throw new BusinessException(BusinessErrorCode.NO_USER, "用户不存在");
//        }
//        JSONObject json = new JSONObject();
//        TokenDTO token = new TokenDTO();
//        token.setUser(userMap.mapToAccountDto(user));
//        token.setPermissions(functionRepository.selectAllByUserId(user.getId()).stream().map(PermissionEntity::getKey).collect(Collectors.toList()));
//        token.setValue(JwtUtil.GenerateToken(user.getLoginName(), user.getPassword()));
//        json.put("token", token);
//        return json;
//    }

//    @Override
//    public JSONObject register(LoginDTO dto) {
//        UserEntity user = super.selectOne(UserEntity::getLoginName, dto.getLoginName());
//        super.update(user);
//        return null;
//    }

    @Override
    public List<PermissionEntity> getFunctionsByUser(String userId) {
        return functionRepository.selectAllByUserId(userId);
    }
}
