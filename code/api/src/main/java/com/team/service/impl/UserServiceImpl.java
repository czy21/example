package com.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.team.core.exception.ErrorCode;
import com.team.core.exception.ServiceException;
import com.team.core.exception.WebException;
import com.team.core.extension.StringExtension;
import com.team.entity.map.UserMap;
import com.team.entity.po.User;
import com.team.entity.vo.UserDto;
import com.team.service.UserService;
import com.team.core.universal.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description User 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMap userMap;

    @Override
    public UserDto insertDefaultPwd(UserDto dto) {
        if (super.SelectBy("LoginName", dto.getLoginName()) != null) {
            throw new WebException(ErrorCode.NAME_EXIST, "账号已存在");
        }
        User user = userMap.toUser(dto);
        user.setPassword("123456");
        return userMap.toUserDto(super.InsertAndGetEntity(user));
    }

    @Override
    public UserDto editUser(UserDto dto) {
        if (StringExtension.StringIsNullOrEmpty(dto.getUserId())) {
            throw new WebException(ErrorCode.ID_NO_EXIST, "用户Id不能为空");
        }
        if (StringExtension.StringIsNullOrEmpty(dto.getUserName())) {
            throw new WebException(ErrorCode.NAME_NO_EXIST, "用户姓名不能为空");
        }
        return userMap.toUserDto(super.UpdateAndGetEntity(userMap.toUser(dto)));
    }

    @Override
    public Boolean modifiedUser(UserDto dto) {
        if (StringExtension.StringIsNullOrEmpty(dto.getUserId())) {
            throw new WebException(ErrorCode.ID_NO_EXIST, "用户Id不能为空");
        }
        User tempMap = userMap.toUser(dto);
        super.Update(tempMap);
        return tempMap.getEnabled();
    }
}
