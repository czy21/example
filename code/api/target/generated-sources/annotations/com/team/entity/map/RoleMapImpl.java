package com.team.entity.map;

import com.team.core.universal.PageModel;
import com.team.entity.po.Role;
import com.team.entity.vo.PageDto;
import com.team.entity.vo.PageParams;
import com.team.entity.vo.RoleDto;
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
public class RoleMapImpl implements RoleMap {

    @Override
    public PageDto<RoleDto> toPageDto(PageModel<Role> page) {
        if ( page == null ) {
            return null;
        }

        PageDto<RoleDto> pageDto = new PageDto<RoleDto>();

        pageDto.setPage( rolePageModelToPageParams( page ) );
        pageDto.setList( roleListToRoleDtoList( page.getList() ) );

        return pageDto;
    }

    protected PageParams rolePageModelToPageParams(PageModel<Role> pageModel) {
        if ( pageModel == null ) {
            return null;
        }

        PageParams pageParams = new PageParams();

        pageParams.setPageSize( pageModel.getPageSize() );
        pageParams.setTotal( pageModel.getTotal() );
        pageParams.setPageIndex( pageModel.getPageIndex() );

        return pageParams;
    }

    protected RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setRoleId( role.getRoleId() );
        roleDto.setRoleName( role.getRoleName() );
        roleDto.setRemark( role.getRemark() );
        roleDto.setEnabled( role.getEnabled() );

        return roleDto;
    }

    protected List<RoleDto> roleListToRoleDtoList(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleDto> list1 = new ArrayList<RoleDto>( list.size() );
        for ( Role role : list ) {
            list1.add( roleToRoleDto( role ) );
        }

        return list1;
    }
}
