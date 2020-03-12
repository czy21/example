package com.team.application.model.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class UserExcelDTO {
    @ExcelProperty(value = "用户姓名", index = 0)
    private String name;
}
