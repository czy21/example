package com.team.domain.mapper;

import java.util.List;

public interface TableMapper {
    List<String> selectColumns(String tableName);
}
