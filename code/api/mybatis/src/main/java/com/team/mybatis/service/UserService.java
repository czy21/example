package com.team.mybatis.service;

import com.team.mybatis.entity.UserPO;

import java.util.List;

public interface UserService {

    List<UserPO> findAll();
}
