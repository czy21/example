package com.team.entity.map;

import com.team.core.universal.PageModel;
import com.team.entity.po.User;
import com.team.entity.vo.LoginDto;
import com.team.entity.vo.PageDto;
import com.team.entity.vo.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMap {
    UserDto toUserDto(User user);

    LoginDto toLoginDto(User user);

    List<UserDto> toUserDtos(List<User> users);

    @Mapping(source = "pageIndex", target = "page.pageIndex")
    @Mapping(source = "pageSize", target = "page.pageSize")
    @Mapping(source = "total", target = "page.total")
    PageDto<UserDto> toPageDto(PageModel<User> page);
}
