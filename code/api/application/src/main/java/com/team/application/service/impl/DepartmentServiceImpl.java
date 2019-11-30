package com.team.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.application.base.MybatisBaseServiceImpl;
import com.team.application.exception.BusinessErrorCode;
import com.team.application.exception.BusinessException;
import com.team.application.model.automap.DepartmentAutoMap;
import com.team.application.model.dto.DepartmentDTO;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.vo.SearchDepartmentModel;
import com.team.application.service.DepartmentService;
import com.team.domain.entity.DepartmentEntity;
import com.team.domain.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


@Service
public class DepartmentServiceImpl extends MybatisBaseServiceImpl<DepartmentMapper, DepartmentEntity> implements DepartmentService {

    @Resource
    private DepartmentAutoMap departmentMap;

    @Override
    public PageDTO<DepartmentDTO> getDepartmentPageListBy(SearchDepartmentModel search) {
        return departmentMap.mapToPageDto(super.selectAll(search.getPageIndex(), search.getPageSize(), (QueryWrapper<DepartmentEntity>) null));
    }

    @Override
    public DepartmentDTO insertDepartment(DepartmentDTO dto) {
        if (StringUtils.isEmpty(dto.getParentId())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "上级部门Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getCompanyId())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "公司Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getDepartmentName())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_NAME, "部门名称不能为空");
        }
        if (super.selectOne(DepartmentEntity::getName, dto.getDepartmentName()) != null) {
            throw new BusinessException(BusinessErrorCode.EXIST_NAME, "部门名称已存在");
        }

        return departmentMap.mapToDto(super.insertAndGet(departmentMap.mapToEntity(dto)));
    }

    @Override
    public DepartmentDTO editDepartment(DepartmentDTO dto) {
        if (StringUtils.isEmpty(dto.getCompanyId())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "公司Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getParentId())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_ID, "上级部门Id不能为空");
        }
        if (StringUtils.isEmpty(dto.getDepartmentName())) {
            throw new BusinessException(BusinessErrorCode.NO_NULL_NAME, "上级部门Id不能为空");
        }
        return departmentMap.mapToDto(super.updateAndGet(departmentMap.mapToEntity(dto)));
    }
}
