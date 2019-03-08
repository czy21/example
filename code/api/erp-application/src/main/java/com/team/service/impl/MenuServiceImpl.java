package com.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.core.universal.MybatisBaseServiceImpl;
import com.team.entity.dto.MenuDto;
import com.team.entity.dto.PageDto;
import com.team.entity.map.MenuMap;
import com.team.entity.mybatis.system.Menu;
import com.team.exception.BusinessException;
import com.team.exception.ErrorCode;
import com.team.extension.MenuExtension;
import com.team.model.SearchMenuModel;
import com.team.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * @Description Menu 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class MenuServiceImpl extends MybatisBaseServiceImpl<Menu> implements MenuService {

    @Resource
    private MenuMap menuMap;

    @Override
    public MenuDto insertMenu(MenuDto dto) {
        if (StringUtils.isEmpty(dto.getParentId())) {
            throw new BusinessException(ErrorCode.ID_NO_NULL, "上级菜单Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getMenuName())) {
            throw new BusinessException(ErrorCode.NAME_NO_NULL, "菜单名称不能为空");
        }
        if (super.SelectBy(Menu::getMenuName, dto.getMenuName()) != null) {
            throw new BusinessException(ErrorCode.NAME_EXIST, "菜单名称已存在");
        }
        if (!dto.getUrl().equals("#") && super.SelectBy(Menu::getUrl, dto.getUrl()) != null) {
            throw new BusinessException(ErrorCode.NAME_EXIST, "菜单地址已存在");
        }
        QueryWrapper<Menu> countQuery = new QueryWrapper<>();
        countQuery.lambda().eq(Menu::getParentId, dto.getParentId());
        dto.setSort(super.SelectListBy(countQuery).stream().map(Menu::getSort).reduce(0, Integer::max) + 1);
        return menuMap.mapToDto(super.InsertAndGetEntity(menuMap.mapToEntity(dto)));
    }

    @Override
    public MenuDto editMenu(MenuDto dto) {
        if (StringUtils.isEmpty(dto.getMenuId())) {
            throw new BusinessException(ErrorCode.ID_NO_NULL, "菜单Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getParentId())) {
            throw new BusinessException(ErrorCode.ID_NO_NULL, "上级菜单Id不能为空");
        }
        return menuMap.mapToDto(super.UpdateAndGetEntity(menuMap.mapToEntity(dto)));
    }

    @Override
    public Integer deleteMenu(Long menuId) {
        return super.DeleteByIds(MenuExtension.getSons(super.SelectListBy(null), menuId).stream().map(Menu::getMenuId).collect(Collectors.toList()));
    }

    @Override
    public PageDto<MenuDto> getMenuPageListBy(SearchMenuModel search) {
        QueryWrapper<Menu> query = new QueryWrapper<>();
        if (StringUtils.isEmpty(search.getMenuName())) {
            query.lambda().like(Menu::getMenuName, search.getMenuName());
        }
        return menuMap.mapToPageDto(super.SelectPageListBy(search.getPageIndex(), search.getPageSize(), query));
    }

}
