package com.team.application.model.automap;

import com.team.domain.entity.UserEntity;
import com.team.application.model.dto.AccountDTO;
import com.team.application.model.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface UserAutoMap extends BaseAutoMap<UserEntity, UserDTO> {
    AccountDTO mapToAccountDto(UserEntity user);
}
