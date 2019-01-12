package com.team.entity.map;

import com.team.entity.page.PageModel;
import com.team.entity.mybatis.system.User;
import com.team.entity.dto.AccountDto;
import com.team.entity.dto.PageDto;
import com.team.entity.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(config = CentralConfig.class)
public interface UserMap {
    UserDto toUserDto(User user);

    User toUser(UserDto dto);

    AccountDto toAccountDto(User user);

    List<UserDto> toUserDtos(List<User> users);

    @Mappings({
            @Mapping(source = "pageIndex", target = "page.pageIndex"),
            @Mapping(source = "pageSize", target = "page.pageSize"),
            @Mapping(source = "total", target = "page.total")})
    PageDto<UserDto> toPageDto(PageModel<User> page);
}
