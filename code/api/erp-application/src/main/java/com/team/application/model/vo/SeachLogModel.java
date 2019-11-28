package com.team.application.model.vo;

import com.team.application.model.page.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SeachLogModel extends PageParams {
    private String description;
    private String method;
    private String requestIp;
    private String exceptionCode;
    private String exceptionDetail;
    private Boolean logType;
    private String addedTimeSort;

}
