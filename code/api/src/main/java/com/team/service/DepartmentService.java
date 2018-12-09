package com.team.service;

import com.team.entity.po.Department;
import com.team.core.universal.BaseService;
import com.team.entity.vo.DepartmentDto;
import com.team.entity.vo.PageDto;
import com.team.model.SearchDepartmentModel;

/**
 * @Description Department 服务类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
public interface DepartmentService extends BaseService<Department> {

    PageDto<DepartmentDto> getDepartmentPageListBy(SearchDepartmentModel search);

    DepartmentDto insertDepartment(DepartmentDto dto);

    DepartmentDto editDepartment(DepartmentDto dto);

    Boolean modifiedDepartment(DepartmentDto dto);
}
