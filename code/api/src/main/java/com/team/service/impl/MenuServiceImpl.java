package com.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.core.exception.ErrorCode;
import com.team.core.exception.WebException;
import com.team.core.extension.StringExtension;
import com.team.core.extension.entity.MenuExtensions;
import com.team.core.universal.BaseServiceImpl;
import com.team.core.util.PageUtil;
import com.team.entity.map.MenuMap;
import com.team.entity.po.Menu;
import com.team.entity.vo.MenuDto;
import com.team.entity.vo.PageDto;
import com.team.model.SearchPermissionModel;
import com.team.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description Menu 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

    @Resource
    private MenuMap menuMap;

    @Override
    public PageDto<MenuDto> getMenuAndPermissionPageListBy(SearchPermissionModel search) {
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.orderByDesc("IsMenu");
        return menuMap.toPageDto(super.SelectPageList(search.getPageIndex(), search.getPageSize(), MenuExtensions.GetSons(super.SelectListBy(query), search.getMenuId())));
    }

    @Override
    public MenuDto insertMenu(MenuDto dto) {
        if (StringExtension.StringIsNullOrEmpty(dto.getParentId())) {
            throw new WebException(ErrorCode.ID_NO_NULL, "上级菜单Id不能为空");
        }
        if (super.SelectBy("MenuName", dto.getMenuName()) != null) {
            throw new WebException(ErrorCode.NAME_EXIST, "菜单或权限名称已存在");
        }
        if (StringExtension.StringIsNullOrEmpty(dto.getMenuName())) {
            throw new WebException(ErrorCode.NAME_NO_NULL, "菜单或权限名称不能为空");
        }
        return menuMap.toMenuDto(super.UpdateAndGetEntity(menuMap.toMenu(dto)));
    }

    @Override
    public MenuDto editMenu(MenuDto dto) {
        return null;
    }
}
