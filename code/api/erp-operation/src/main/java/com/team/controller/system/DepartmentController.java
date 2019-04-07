package com.team.controller.system;


import com.team.core.annotations.Pocket;
import com.team.entity.dto.DepartmentDto;
import com.team.entity.dto.PageDto;
import com.team.entity.mybatis.system.Company;
import com.team.entity.mybatis.system.Department;
import com.team.model.SearchDepartmentModel;
import com.team.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description Department 前端控制器
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@RestController
@RequestMapping("api/department")
@Api(tags = "Department", description = "部门操作接口")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("load")
    @Pocket(entity = Company.class)
    @Pocket(entity = Department.class)
    @ApiOperation(value = "加载部门列表")
    @PreAuthorize("hasAuthority('SearchDepartment')")
    public PageDto<DepartmentDto> Load(SearchDepartmentModel search) {
        return departmentService.getDepartmentPageListBy(search);
    }

    @PostMapping("search")
    @ApiOperation(value = "查询部门列表")
    @PreAuthorize("hasAuthority('SearchDepartment')")
    public PageDto<DepartmentDto> Search(SearchDepartmentModel search) {
        return departmentService.getDepartmentPageListBy(search);
    }

    @PostMapping("add")
    @ApiOperation(value = "添加部门信息")
    @PreAuthorize("hasAuthority('AddDepartment')")
    public DepartmentDto Add(DepartmentDto dto) {
        return departmentService.insertDepartment(dto);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改部门信息")
    @PreAuthorize("hasAuthority('EditDepartment')")
    public DepartmentDto Edit(DepartmentDto dto) {
        return departmentService.editDepartment(dto);
    }

    @PostMapping("modified")
    @ApiOperation(value = "更改部门状态")
    @PreAuthorize("hasAuthority('DisableDepartment')")
    public Boolean Modified(DepartmentDto dto) {
        return departmentService.modifiedDepartment(dto);
    }
}