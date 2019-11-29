package com.team.application.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogDTO {
    private String logId;
    private String description;
    private String method;
    private Boolean logType;
    private String requestIp;
    private String exceptionCode;
    private String exceptionDetail;
    private String operatorName;
    private Integer spendTime;
    private LocalDateTime addedTime;
}
