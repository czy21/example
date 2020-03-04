package com.team.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.team.infrastructure.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "sys_log")
@EqualsAndHashCode(callSuper = true)
public class LogEntity extends BaseEntity {

    /**
     * 日志信息描述
     */
    private String description;
    /**
     * 方法名称
     */
    private String method;
    /**
     * 日志类型 0是正常，1是异常
     */
    private Boolean logType;
    /**
     * 请求的ip
     */
    private String requestIp;
    /**
     * 异常错误码
     */
    private String exceptionCode;
    /**
     * 异常详情
     */
    private String exceptionDetail;

    /**
     * 请求时长
     */
    private Integer spendTime;

    /**
     * 操作人
     */
    private UserEntity operator;
    /**
     * 日志类型为正常
     */
    @TableField(exist = false)
    public static final Boolean logInfo = false;
    /**
     * 日志类型为异常
     */
    @TableField(exist = false)
    public static final Boolean logError = true;
}
