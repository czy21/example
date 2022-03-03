package com.team.portal.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserExportDTO {
    @ExcelProperty(value = "name")
    private String name;
}
