package com.team.application.service;

import com.team.application.core.universal.MybatisBaseService;
import com.team.domain.entity.DepartmentEntity;
import com.team.application.model.dto.DepartmentDTO;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.vo.SearchDepartmentModel;

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
