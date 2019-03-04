package com.team.service.impl;

import com.team.core.universal.MybatisBaseServiceImpl;
import com.team.entity.dto.DepartmentDto;
import com.team.entity.dto.PageDto;
import com.team.entity.map.DepartmentMap;
import com.team.entity.mybatis.system.Department;
import com.team.exception.ErrorCode;
import com.team.exception.BusinessException;
import com.team.model.SearchDepartmentModel;
import com.team.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @Description Department 服务实现类
 * @Author 陈昭宇
 * @Date 2018-10-15
 */
@Service
public class DepartmentServiceImpl extends MybatisBaseServiceImpl<Department> implements DepartmentService {

    @Resource
    private DepartmentMap departmentMap;

    @Override
    public PageDto<DepartmentDto> getDepartmentPageListBy(SearchDepartmentModel search) {
        return departmentMap.mapToPageDto(super.SelectPageListBy(search.getPageIndex(), search.getPageSize(), null));
    }

    @Override
    public DepartmentDto insertDepartment(DepartmentDto dto) {
        if (StringUtils.isEmpty(dto.getParentId())) {
            throw new BusinessException(ErrorCode.ID_NO_NULL, "上级部门Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getCompanyId())) {
            throw new BusinessException(ErrorCode.ID_NO_NULL, "公司Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getDepartmentName())) {
            throw new BusinessException(ErrorCode.NAME_NO_NULL, "部门名称不能为空");
        }
        if (super.SelectBy(Department::getDepartmentName, dto.getDepartmentName()) != null) {
            throw new BusinessException(ErrorCode.NAME_EXIST, "部门名称已存在");
        }

        return departmentMap.mapToDto(super.InsertAndGetEntity(departmentMap.mapToEntity(dto)));
    }

    @Override
    public DepartmentDto editDepartment(DepartmentDto dto) {
        if (StringUtils.isEmpty(dto.getCompanyId())) {
            throw new BusinessException(ErrorCode.ID_NO_NULL, "公司Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getParentId())) {
            throw new BusinessException(ErrorCode.ID_NO_NULL, "上级部门Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getDepartmentName())) {
            throw new BusinessException(ErrorCode.NAME_NO_NULL, "上级部门Id不能为空");
        }
        return departmentMap.mapToDto(super.UpdateAndGetEntity(departmentMap.mapToEntity(dto)));
    }

    @Override
    public Boolean modifiedDepartment(DepartmentDto dto) {
        if (StringUtils.isEmpty(dto.getDepartmentId())) {
            throw new BusinessException(ErrorCode.ID_NO_NULL, "部门Id不能为空");
        }
        return super.UpdateAndGetEntity(departmentMap.mapToEntity(dto)).getEnabled();

    }
}
