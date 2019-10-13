package com.team.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.team.core.universal.MybatisBaseServiceImpl;
import com.team.entity.dto.LoginDto;
import com.team.entity.dto.PageDto;
import com.team.entity.dto.TokenDto;
import com.team.entity.dto.UserDto;
import com.team.entity.map.MenuMap;
import com.team.entity.map.UserMap;
import com.team.entity.mybatis.system.Function;
import com.team.entity.mybatis.system.Role;
import com.team.entity.mybatis.system.User;
import com.team.exception.BusinessErrorCode;
import com.team.exception.BusinessException;
import com.team.extension.MenuExtension;
import com.team.model.SearchUserModel;
import com.team.repository.mybatis.system.FunctionRepository;
import com.team.repository.mybatis.system.MenuRepository;
import com.team.repository.mybatis.system.RoleRepository;
import com.team.service.UserService;
import com.team.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description User 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class UserServiceImpl extends MybatisBaseServiceImpl<User> implements UserService {

    @Resource
    private UserMap userMap;
    @Resource
    private MenuMap menuMap;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private FunctionRepository functionRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public UserDto insertDefaultPwd(UserDto dto) {
        if (StringUtils.isEmpty(dto.getUserName())) {
            throw new BusinessException(BusinessErrorCode.EXIST_NAME, "用户姓名不能为空");
        }
        if (super.SelectBy(User::getLoginName, dto.getLoginName()) != null) {
            throw new BusinessException(BusinessErrorCode.EXIST_USER, "用户账号已存在");
        }
        User user = userMap.mapToEntity(dto);
        user.setPassword("123456");
        return userMap.mapToDto(super.InsertAndGetEntity(user));
    }

    @Override
    public UserDto editUser(UserDto dto) {
        if (StringUtils.isEmpty(dto.getUserId())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "用户Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getUserName())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_NAME, "用户姓名不能为空");
        }
        return userMap.mapToDto(super.UpdateAndGetEntity(userMap.mapToEntity(dto)));
    }

    @Override
    public Boolean modifiedUser(UserDto dto) {
        if (StringUtils.isEmpty(dto.getUserId())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "用户Id不能为空");
        }
        return super.UpdateAndGetEntity(userMap.mapToEntity(dto)).getEnabled();
    }

    @Override
    public PageDto<UserDto> getUserPageListBy(SearchUserModel search) {
        return userMap.mapToPageDto(super.SelectPageListBy(search.getPageIndex(), search.getPageSize(), null));
    }

    @Override
    public List<Role> getRolesByLoginName(String loginName) {
        return roleRepository.getRolesByLoginName(loginName);
    }

    @Override
    public List<Function> getFunctionsByRole(List<Long> roleIds) {
        return functionRepository.getFunctionsByRoleIds(roleIds);
    }

    @Override
    public JSONObject login(LoginDto dto) {
        User user = super.SelectBy(User::getLoginName, dto.getLoginName());
        if (ObjectUtils.isEmpty(user)) {
            throw new BusinessException(BusinessErrorCode.NO_USER, "用户不存在");
        }
        JSONObject json = new JSONObject();
        TokenDto token = new TokenDto();
        token.setUser(userMap.mapToAccountDto(user));
        token.setMenus(MenuExtension.createTreeMenus(menuMap.toMenuTree(menuRepository.getMenusByUserId(user.getUserId()))));
        token.setPermissions(functionRepository.getFunctionsByUserId(user.getUserId()).stream().map(Function::getFunctionCode).collect(Collectors.toList()));
        token.setValue(JwtUtil.GenerateToken(user.getLoginName(), user.getPassword()));
        json.put("token", token);
        return json;
    }

    @Override
    public JSONObject register(LoginDto dto) {
        User user = super.SelectBy(User::getLoginName, dto.getLoginName());
        super.Update(user);
        return null;
    }

    @Override
    public List<Function> getFunctionsByUser(Long userId) {
        return functionRepository.getFunctionsByUserId(userId);
    }
}
