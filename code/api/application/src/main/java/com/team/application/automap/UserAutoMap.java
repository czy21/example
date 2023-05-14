package com.team.application.automap;

import com.team.application.model.dto.AccountDTO;
import com.team.application.model.dto.UserDTO;
import com.team.application.service.DictService;
import com.team.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = CentralConfig.class)
public abstract class UserAutoMap implements BaseAutoMap<UserEntity, UserDTO> {
    @Autowired
    DictService dictService;

    @Mappings({
            @Mapping(target = "statusName", expression = "java(dictService.findSimpleLabel(\"status\",entity.getStatus()))")
    })
    @Override
    public abstract UserDTO mapToTarget(UserEntity entity);

    abstract AccountDTO mapToAccountDto(UserEntity user);
}
