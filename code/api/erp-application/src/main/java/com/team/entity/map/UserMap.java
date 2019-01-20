package com.team.entity.map;

import com.team.entity.dto.AccountDto;
import com.team.entity.dto.UserDto;
import com.team.entity.mybatis.system.User;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface UserMap extends BaseMap<User, UserDto> {
    AccountDto mapToAccountDto(User user);
}
