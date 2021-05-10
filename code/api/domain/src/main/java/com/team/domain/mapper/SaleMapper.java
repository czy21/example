package com.team.domain.mapper;

import com.team.domain.entity.SaleEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SaleMapper {

    @Select("select bf.* from ent_datacenter_inspect_sale bf limit #{_skiprows},#{_pagesize}")
    List<Map<String,Object>> selectAllByPage(@Param("_skiprows") int skipRow, @Param("_pagesize") int pageSize);

    @Insert("insert into ent_sfl_inspect_sale_1 (id,from_institution_code,from_institution_name,to_institution_code,to_institution_name,product_code,product_name,product_spec,product_unit) " +
            " values " +
            "(#{id},#{fromInstitutionCode},#{fromInstitutionName},#{toInstitutionCode},#{toInstitutionName},#{productCode},#{productName},#{productSpec},#{productUnit})")
    void insert(SaleEntity entity);

}
