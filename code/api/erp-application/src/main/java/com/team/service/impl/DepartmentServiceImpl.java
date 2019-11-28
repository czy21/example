package com.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.core.universal.MybatisBaseServiceImpl;
import com.team.domain.entity.DepartmentEntity;
import com.team.entity.dto.DepartmentDTO;
import com.team.entity.dto.PageDTO;
import com.team.entity.map.DepartmentAutoMap;
import com.team.exception.BusinessErrorCode;
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
public class DepartmentServiceImpl extends MybatisBaseServiceImpl<DepartmentEntity> implements DepartmentService {

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
