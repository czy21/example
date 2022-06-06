package com.team.domain.mapper;

import com.team.domain.entity.SaleEntity;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;

public interface SaleMapper {

    @Select("select * from ent_sale")
    @Options(fetchSize = Integer.MIN_VALUE)
    Cursor<SaleEntity> selectAllForCursor();

}
