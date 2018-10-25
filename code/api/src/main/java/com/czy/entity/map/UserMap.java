package com.czy.entity.map;

import com.czy.entity.po.User;
import com.czy.entity.vo.LoginDto;
import com.czy.entity.vo.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMap {
    UserDto toUserDto(User user);
    LoginDto toLoginDto(User user);
    List<UserDto> toUserDtos(List<User> users);
}
