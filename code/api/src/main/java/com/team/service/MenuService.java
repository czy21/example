package com.team.service;

import com.team.core.universal.BaseService;
import com.team.core.universal.PageModel;
import com.team.core.util.PageUtil;
import com.team.entity.po.Menu;
import com.team.model.SearchPermissionModel;

import java.util.List;

/**
 * @Description Menu 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface MenuService extends BaseService<Menu> {

    PageUtil<Menu> getMenuAndPermissionPageListBy(SearchPermissionModel search);

}
