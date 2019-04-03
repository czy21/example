package com.team.entity.map;

import com.team.core.util.TreeUtil.Node;
import com.team.entity.po.Menu;
import com.team.entity.vo.MenuDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-04-03T09:48:09+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_192 (Oracle Corporation)"
)
@Component
public class MenuMapImpl implements MenuMap {

    @Override
    public List<MenuDto> toMenuDtos(List<Menu> menus) {
        if ( menus == null ) {
            return null;
        }

        List<MenuDto> list = new ArrayList<MenuDto>( menus.size() );
        for ( Menu menu : menus ) {
            list.add( menuToMenuDto( menu ) );
        }

        return list;
    }

    @Override
    public List<Node> toMenuTree(List<Menu> menus) {
        if ( menus == null ) {
            return null;
        }

        List<Node> list = new ArrayList<Node>( menus.size() );
        for ( Menu menu : menus ) {
            list.add( menuToNode( menu ) );
        }

        return list;
    }

    protected MenuDto menuToMenuDto(Menu menu) {
        if ( menu == null ) {
            return null;
        }

        MenuDto menuDto = new MenuDto();

        menuDto.setMenuId( menu.getMenuId() );
        menuDto.setParentId( menu.getParentId() );
        menuDto.setMenuName( menu.getMenuName() );
        menuDto.setIcon( menu.getIcon() );
        menuDto.setSort( menu.getSort() );
        menuDto.setUrl( menu.getUrl() );
        menuDto.setIsMenu( menu.getIsMenu() );
        menuDto.setRemark( menu.getRemark() );
        menuDto.setEnabled( menu.getEnabled() );

        return menuDto;
    }

    protected Node menuToNode(Menu menu) {
        if ( menu == null ) {
            return null;
        }

        Node node = new Node();

        node.setMenuId( menu.getMenuId() );
        node.setParentId( menu.getParentId() );
        node.setMenuName( menu.getMenuName() );
        node.setIcon( menu.getIcon() );
        node.setUrl( menu.getUrl() );

        return node;
    }
}
