package com.team.service.impl;

import com.team.core.exception.ErrorCode;
import com.team.core.exception.WebException;
import com.team.entity.map.DepartmentMap;
import com.team.entity.po.Department;
import com.team.entity.vo.DepartmentDto;
import com.team.entity.vo.PageDto;
import com.team.model.SearchDepartmentModel;
import com.team.service.DepartmentService;
import com.team.core.universal.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @Description Department 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

    @Resource
    private DepartmentMap departmentMap;

    @Override
    public PageDto<DepartmentDto> getDepartmentPageListBy(SearchDepartmentModel search) {
        return departmentMap.toPageDto(super.SelectPageListBy(search.getPageIndex(), search.getPageSize(), null));
    }

    @Override
    public DepartmentDto insertDepartment(DepartmentDto dto) {
        if (StringUtils.isEmpty(dto.getParentId())) {
            throw new WebException(ErrorCode.ID_NO_NULL, "上级部门Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getCompanyId())) {
            throw new WebException(ErrorCode.ID_NO_NULL, "公司Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getDepartmentName())) {
            throw new WebException(ErrorCode.NAME_NO_NULL, "部门名称不能为空");
        }
        if (super.SelectBy(Department::getDepartmentName, dto.getDepartmentName()) != null) {
            throw new WebException(ErrorCode.NAME_EXIST, "部门名称已存在");
        }

        return departmentMap.toDepartmentDto(super.InsertAndGetEntity(departmentMap.toDepartment(dto)));
    }

    @Override
    public DepartmentDto editDepartment(DepartmentDto dto) {
        if (StringUtils.isEmpty(dto.getCompanyId())) {
            throw new WebException(ErrorCode.ID_NO_NULL, "公司Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getParentId())) {
            throw new WebException(ErrorCode.ID_NO_NULL, "上级部门Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getDepartmentName())) {
            throw new WebException(ErrorCode.NAME_NO_NULL, "上级部门Id不能为空");
        }
        return departmentMap.toDepartmentDto(super.UpdateAndGetEntity(departmentMap.toDepartment(dto)));
    }

    @Override
    public Boolean modifiedDepartment(DepartmentDto dto) {
        if (StringUtils.isEmpty(dto.getDepartmentId())) {
            throw new WebException(ErrorCode.ID_NO_NULL, "部门Id不能为空");
        }
        return super.UpdateAndGetEntity(departmentMap.toDepartment(dto)).getEnabled();

    }
}
