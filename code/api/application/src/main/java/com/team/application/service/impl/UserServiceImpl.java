package com.team.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czy.learning.db.annotation.DS;
import com.czy.learning.infranstructure.model.SimpleItemModel;
import com.czy.learning.web.annotation.Option;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.application.automap.UserAutoMap;
import com.team.application.file.EasyExcelReadService;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.UserDTO;
import com.team.application.model.dto.UserImportDTO;
import com.team.application.model.page.PageModel;
import com.team.application.model.vo.SearchVO;
import com.team.application.service.UserService;
import com.team.domain.entity.UserEntity;
import com.team.domain.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Resource
    UserAutoMap userAutoMap;
    @Autowired
    UserMapper userMapper;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public PageDTO<UserDTO> findByPage(SearchVO<UserDTO> search) {
        Page<UserEntity> page = new Page<>(search.getPage().getPageIndex(), search.getPage().getPageSize());
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .orderByDesc(UserEntity::getCreateTime);
        return userAutoMap.mapToPageTarget(PageModel.of(super.page(page, queryWrapper)));
    }

    @Override
    public void importUser(MultipartFile file) throws IOException {
        EasyExcelReadService<UserImportDTO> easyExcelReadService = new EasyExcelReadService<UserImportDTO>().file(file.getInputStream())
                .process(ctx -> {
                    ctx.getRows().forEach(t -> {
                        if (t.getData().getAmount() == null) {
                            ctx.getErrors().get(t.getRowIndex()).add("金额不能为空");
                        }
                        if (t.getData().getAge() == null) {
                            ctx.getErrors().get(t.getRowIndex()).add("年龄不能为空");
                        }
                    });
                }, objectMapper, redisTemplate);
        easyExcelReadService.head(UserImportDTO.class).sheet(0).doRead();
        System.out.println(easyExcelReadService.getToken());
    }

    @DS("circler")
    @Option("option1")
    public List<SimpleItemModel<String>> option1() {
        return userMapper.selectList(new QueryWrapper<>()).stream().map(t -> SimpleItemModel.of(t.getUserName(), t.getId())).collect(Collectors.toList());
    }

    @Option("option2")
    public List<SimpleItemModel<String>> option2() {
        return userMapper.selectList(new QueryWrapper<>()).stream().map(t -> SimpleItemModel.of(t.getUserName(), t.getId())).collect(Collectors.toList());
    }
}
