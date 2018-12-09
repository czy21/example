package com.team.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.team.core.universal.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 系统日志表
 *
 * @author 陈昭宇
 * @since 2018-11-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Log extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId("log_id")
    private String logId;
    /**
     * 日志信息描述
     */
    @TableField("description")
    private String description;
    /**
     * 方法名称
     */
    @TableField("method")
    private String method;
    /**
     * 日志类型 0是正常，1是异常
     */
    @TableField("log_type")
    private Boolean logType;
    /**
     * 请求的ip
     */
    @TableField("request_ip")
    private String requestIp;
    /**
     * 异常错误码
     */
    @TableField("exception_code")
    private String exceptionCode;
    /**
     * 异常详情
     */
    @TableField("exception_detail")
    private String exceptionDetail;
    /**
     * 请求的用户id
     */
    @TableField("user_id")
    private String userId;
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
