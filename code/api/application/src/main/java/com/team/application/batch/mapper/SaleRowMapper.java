package com.team.application.batch.mapper;

import com.team.domain.entity.SaleEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleRowMapper implements RowMapper<SaleEntity> {
    @Override
    public SaleEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        SaleEntity entity = new SaleEntity();
        entity.setId(rs.getString("id"));
        entity.setFromInstitutionCode(rs.getString("fromInstitutionCode"));
        entity.setFromInstitutionName(rs.getString("fromInstitutionName"));
        entity.setToInstitutionCode(rs.getString("toInstitutionCode"));
        entity.setToInstitutionName(rs.getString("toInstitutionName"));
        entity.setProductCode(rs.getString("productCode"));
        entity.setProductName(rs.getString("productName"));
        entity.setProductSpec(rs.getString("productSpec"));
        entity.setProductUnit(rs.getString("productUnit"));
        return entity;
    }
}
