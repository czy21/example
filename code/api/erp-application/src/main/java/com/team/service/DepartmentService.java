package com.team.service;

import com.team.core.universal.MybatisBaseService;
import com.team.domain.entity.DepartmentEntity;
import com.team.entity.dto.DepartmentDTO;
import com.team.entity.dto.PageDTO;
import com.team.model.SearchDepartmentModel;

/**
 * @Description Department 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface DepartmentService extends MybatisBaseService<DepartmentEntity> {

    PageDTO<DepartmentDTO> getDepartmentPageListBy(SearchDepartmentModel search);

    DepartmentDTO insertDepartment(DepartmentDTO dto);

    DepartmentDTO editDepartment(DepartmentDTO dto);
}
