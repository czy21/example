package com.team.controller;


import com.team.core.mvc.Pocket;
import com.team.entity.po.Company;
import com.team.entity.po.Department;
import com.team.entity.vo.DepartmentDto;
import com.team.entity.vo.PageDto;
import com.team.entity.vo.UserDto;
import com.team.model.SearchDepartmentModel;
import com.team.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Pocket(entity = {Company.class, Department.class})
    @ApiOperation(value = "加载部门列表")
    public PageDto<DepartmentDto> Load(SearchDepartmentModel search) {
        return departmentService.getDepartmentPageListBy(search);
    }

    @PostMapping("search")
    @ApiOperation(value = "查询部门列表")
    public PageDto<DepartmentDto> Search(SearchDepartmentModel search) {
        return departmentService.getDepartmentPageListBy(search);
    }

    @PostMapping("add")
    @ApiOperation(value = "添加部门信息")
    public DepartmentDto Add(DepartmentDto dto) {
        return departmentService.insertDepartment(dto);
    }

}