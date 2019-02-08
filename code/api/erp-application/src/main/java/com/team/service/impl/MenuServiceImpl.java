package com.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.core.universal.MybatisBaseServiceImpl;
import com.team.entity.dto.MenuDto;
import com.team.entity.dto.PageDto;
import com.team.entity.dto.PermissionDto;
import com.team.entity.map.MenuMap;
import com.team.entity.mybatis.system.Menu;
import com.team.exception.ErrorCode;
import com.team.exception.WebException;
import com.team.extension.MenuExtension;
import com.team.model.SearchPermissionModel;
import com.team.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
    @SuppressWarnings("unchecked")
    public PageDto<MenuDto> getMenuAndPermissionPageListBy(SearchPermissionModel search) {
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.lambda().orderByDesc(Menu::getIsMenu);
        return menuMap.mapToPageDto(super.SelectPageList(search.getPageIndex(), search.getPageSize(), MenuExtension.getSons(super.SelectListBy(query), search.getMenuId())));
    }

    @Override
    public MenuDto insertMenu(MenuDto dto) {
        if (StringUtils.isEmpty(dto.getParentId())) {
            throw new WebException(ErrorCode.ID_NO_NULL, "上级菜单Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getMenuName())) {
            throw new WebException(ErrorCode.NAME_NO_NULL, "菜单或权限名称不能为空");
        }
        if (super.SelectBy(Menu::getMenuName, dto.getMenuName()) != null) {
            throw new WebException(ErrorCode.NAME_EXIST, "菜单或权限名称已存在");
        }
        if (super.SelectBy(Menu::getUrl, dto.getUrl()) != null) {
            throw new WebException(ErrorCode.NAME_EXIST, "菜单或权限地址已存在");
        }
        QueryWrapper<Menu> countQuery = new QueryWrapper<>();
        countQuery.lambda().eq(Menu::getParentId, dto.getParentId());
        dto.setSort(super.SelectListBy(countQuery).stream().map(Menu::getSort).reduce(0, Integer::max) + 1);
        return menuMap.mapToDto(super.InsertAndGetEntity(menuMap.mapToEntity(dto)));
    }

    @Override
    public MenuDto editMenu(MenuDto dto) {
        if (StringUtils.isEmpty(dto.getMenuId())) {
            if (dto.getIsMenu()) {
                throw new WebException(ErrorCode.ID_NO_NULL, "菜单Id不能为空");
            } else {
                throw new WebException(ErrorCode.ID_NO_NULL, "权限Id不能为空");
            }
        }
        if (StringUtils.isEmpty(dto.getParentId())) {
            throw new WebException(ErrorCode.ID_NO_NULL, "上级菜单Id不能为空");
        }
        return menuMap.mapToDto(super.UpdateAndGetEntity(menuMap.mapToEntity(dto)));
    }

    @Override
    public Integer deleteMenu(String menuId) {
        return super.DeleteByIds(MenuExtension.getSons(super.SelectListBy(null), menuId).stream().map(Menu::getMenuId).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public Boolean batchInsertPermission(List<PermissionDto> dtos) {
        if (CollectionUtils.isEmpty(dtos)) {
            throw new WebException(ErrorCode.ID_NO_NULL, "权限集合不能为空");
        }
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.lambda().eq(Menu::getIsMenu, true);
        List<Menu> menus = super.SelectListBy(query);
        dtos.stream().sorted(Comparator.comparing(PermissionDto::getTag)).forEach(t -> {
            Optional<Menu> temp = menus.stream().filter(m -> m.getUrl().endsWith(t.getTag().toLowerCase())).findFirst();
            temp.ifPresent(m -> {
                QueryWrapper<Menu> countQuery = new QueryWrapper<>();
                countQuery.lambda().eq(Menu::getParentId, m.getMenuId());
                Menu per = super.SelectBy(Menu::getUrl, t.getUrl());
                int count = super.SelectListBy(countQuery).stream().map(Menu::getSort).reduce(0, Integer::max);
                if (per != null) {
                    per.setMenuName(t.getSummary());
                    per.setUrl(t.getUrl());
                    super.Update(per);
                } else {
                    Menu menu = new Menu();
                    menu.setParentId(m.getMenuId());
                    menu.setIsMenu(false);
                    menu.setMenuName(t.getSummary());
                    menu.setUrl(t.getUrl());
                    menu.setSort(StringUtils.isEmpty(count) ? 1 : count + 1);
                    super.Insert(menu);
                }
            });
        });
        return true;
    }
}
