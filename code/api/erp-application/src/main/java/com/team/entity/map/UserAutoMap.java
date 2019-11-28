package com.team.entity.map;

import com.team.domain.entity.UserEntity;
import com.team.entity.dto.AccountDTO;
import com.team.entity.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface UserAutoMap extends BaseAutoMap<UserEntity, UserDTO> {
    AccountDTO mapToAccountDto(UserEntity user);
}
