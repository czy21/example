package com.team.entity.map;

import com.team.entity.po.User;
import com.team.entity.vo.LoginDto;
import com.team.entity.vo.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMap {
    UserDto toUserDto(User user);
    LoginDto toLoginDto(User user);
    List<UserDto> toUserDtos(List<User> users);
}
