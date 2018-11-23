package com.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.team.core.extension.entity.MenuExtensions;
import com.team.core.universal.PageModel;
import com.team.entity.po.Menu;
import com.team.model.SearchPermissionModel;
import com.team.service.MenuService;
import com.team.core.universal.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description Menu 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {


    @Override
    public List<Menu> getPermissionPageList(SearchPermissionModel search) {

        return MenuExtensions.GetSons(super.SelectList(), search.getMenuId());
    }
}
