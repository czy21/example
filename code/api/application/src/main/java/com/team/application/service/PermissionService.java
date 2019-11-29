package com.team.application.service;

import com.team.application.core.universal.MybatisBaseService;
import com.team.domain.entity.PermissionEntity;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.PermissionDTO;
import com.team.application.model.vo.SearchFunctionModel;

/**
 * @author 陈昭宇
 * @description Function 服务类
 * @date 2019-03-05
 */
public interface PermissionService extends MybatisBaseService<PermissionEntity> {

    PermissionDTO insertFunction(PermissionDTO dto);

    PermissionDTO editFunction(PermissionDTO dto);

    PageDTO<PermissionDTO> getFunctionPageListBy(SearchFunctionModel search);
}
