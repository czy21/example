package com.team.application.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.team.application.model.dto.UserExcelDTO;

public class UserListener extends AnalysisEventListener<UserExcelDTO> {
    @Override
    public void invoke(UserExcelDTO data, AnalysisContext context) {

        System.out.println("aa");
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("aa");
    }
}
