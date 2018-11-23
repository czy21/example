package com.team.service.impl;

import com.team.core.extension.entity.MenuExtensions;
import com.team.core.universal.BaseServiceImpl;
import com.team.core.util.PageUtil;
import com.team.entity.po.Menu;
import com.team.model.SearchPermissionModel;
import com.team.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * @Description Menu 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {
    @Override
    public PageUtil<Menu> getMenuAndPermissionPageListBy(SearchPermissionModel search) {
        return super.SelectPageList(search.getPageIndex(), search.getPageSize(), MenuExtensions.GetSons(super.SelectList(), search.getMenuId()));
    }
}
