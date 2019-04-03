package com.team.entity.map;

import com.team.core.universal.PageModel;
import com.team.entity.po.User;
import com.team.entity.vo.LoginDto;
import com.team.entity.vo.PageDto;
import com.team.entity.vo.PageParams;
import com.team.entity.vo.UserDto;
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
public class UserMapImpl implements UserMap {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId( user.getUserId() );
        userDto.setLoginName( user.getLoginName() );
        userDto.setUserName( user.getUserName() );
        userDto.setEmail( user.getEmail() );
        userDto.setPhone( user.getPhone() );
        userDto.setIsHeader( user.getIsHeader() );
        userDto.setDepartmentId( user.getDepartmentId() );
        userDto.setEnabled( user.getEnabled() );

        return userDto;
    }

    @Override
    public User toUser(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( dto.getUserId() );
        user.setLoginName( dto.getLoginName() );
        user.setUserName( dto.getUserName() );
        user.setEmail( dto.getEmail() );
        user.setPhone( dto.getPhone() );
        user.setIsHeader( dto.getIsHeader() );
        user.setDepartmentId( dto.getDepartmentId() );
        user.setEnabled( dto.getEnabled() );

        return user;
    }

    @Override
    public LoginDto toLoginDto(User user) {
        if ( user == null ) {
            return null;
        }

        LoginDto loginDto = new LoginDto();

        loginDto.setLoginName( user.getLoginName() );
        loginDto.setUserName( user.getUserName() );

        return loginDto;
    }

    @Override
    public List<UserDto> toUserDtos(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( User user : users ) {
            list.add( toUserDto( user ) );
        }

        return list;
    }

    @Override
    public PageDto<UserDto> toPageDto(PageModel<User> page) {
        if ( page == null ) {
            return null;
        }

        PageDto<UserDto> pageDto = new PageDto<UserDto>();

        pageDto.setPage( userPageModelToPageParams( page ) );
        pageDto.setList( toUserDtos( page.getList() ) );

        return pageDto;
    }

    protected PageParams userPageModelToPageParams(PageModel<User> pageModel) {
        if ( pageModel == null ) {
            return null;
        }

        PageParams pageParams = new PageParams();

        pageParams.setPageSize( pageModel.getPageSize() );
        pageParams.setTotal( pageModel.getTotal() );
        pageParams.setPageIndex( pageModel.getPageIndex() );

        return pageParams;
    }
}
