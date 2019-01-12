package com.team.service;

import com.team.core.universal.MybatisBaseService;
import com.team.entity.dto.DepartmentDto;
import com.team.entity.dto.PageDto;
import com.team.entity.mybatis.system.Department;
import com.team.model.SearchDepartmentModel;

/**
 * @Description Department 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface DepartmentService extends MybatisBaseService<Department> {

    PageDto<DepartmentDto> getDepartmentPageListBy(SearchDepartmentModel search);

    DepartmentDto insertDepartment(DepartmentDto dto);

    DepartmentDto editDepartment(DepartmentDto dto);

    Boolean modifiedDepartment(DepartmentDto dto);
}
