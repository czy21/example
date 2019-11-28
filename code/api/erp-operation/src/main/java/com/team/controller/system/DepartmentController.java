package com.team.controller.system;


import com.team.entity.dto.DepartmentDTO;
import com.team.entity.dto.PageDTO;
import com.team.model.SearchDepartmentModel;
import com.team.service.DepartmentService;
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
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("load")
    public PageDTO<DepartmentDTO> Load(SearchDepartmentModel search) {
        return departmentService.getDepartmentPageListBy(search);
    }

    @PostMapping("search")
    public PageDTO<DepartmentDTO> Search(SearchDepartmentModel search) {
        return departmentService.getDepartmentPageListBy(search);
    }

    @PostMapping("add")
    public DepartmentDTO Add(DepartmentDTO dto) {
        return departmentService.insertDepartment(dto);
    }

    @PostMapping("edit")
    public DepartmentDTO Edit(DepartmentDTO dto) {
        return departmentService.editDepartment(dto);
    }
}