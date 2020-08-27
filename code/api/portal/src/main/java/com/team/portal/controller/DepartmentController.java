package com.team.portal.controller;


import com.team.application.model.dto.DepartmentDTO;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.vo.SearchDepartmentModel;
import com.team.application.service.DepartmentService;
import com.team.domain.repository.jpa.DepartmentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    DepartmentJpaRepository departmentJpaRepository;

    @GetMapping(path = "jpaLoad")
    private Object jpaLoad() {

        List<String> companys = departmentJpaRepository.findAll().stream().map(s -> s.getCompany().getName()).collect(Collectors.toList());
        return companys;
    }


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