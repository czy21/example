package com.team.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.application.automap.UserAutoMap;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.UserDTO;
import com.team.application.model.page.PageModel;
import com.team.application.model.vo.SearchVO;
import com.team.application.service.UserService;
import com.team.domain.entity.UserEntity;
import com.team.domain.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Resource
    UserAutoMap userAutoMap;

    @Override
    public PageDTO<UserDTO> findByPage(SearchVO<UserDTO> search) {
        Page<UserEntity> page = new Page<>(search.getPage().getPageIndex(), search.getPage().getPageSize());
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .orderByDesc(UserEntity::getCreateTime);
        return userAutoMap.mapToPageTarget(PageModel.of(super.page(page, queryWrapper)));
    }
}
