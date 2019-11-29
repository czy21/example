package com.team.application.service.impl;

import com.team.application.core.universal.MybatisBaseServiceImpl;
import com.team.application.service.CompanyService;
import com.team.domain.entity.CompanyEntity;
import com.team.domain.mapper.CompanyMapper;
import org.springframework.stereotype.Service;


@Service
public class CompanyServiceImpl extends MybatisBaseServiceImpl<CompanyMapper, CompanyEntity> implements CompanyService {

}
