package com.team.domain.mapper;

import com.team.domain.dto.TaskDo;
import com.team.domain.entity.TaskEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper {

    List<TaskDo> selectList(@Param("query") TaskEntity query);
    void updateOne(@Param("query") TaskEntity query);
}
