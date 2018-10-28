package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * 系统日志表
 *
 * @author 陈昭宇
 * @since 2018-10-28
 */
@Data
public class Log {
    @TableId
    private String LogId;
    /**
     * 日志信息描述
     */
    private String Description;
    /**
     * 方法名称
     */
    private String Method;
    /**
     * 日志类型 0是正常，1是异常
     */
    private Boolean LogType;
    /**
     * 请求的ip
     */
    private String RequestIp;
    /**
     * 异常错误码
     */
    private String ExceptionCode;
    /**
     * 异常详情
     */
    private String ExceptionDetail;
    /**
     * 请求的用户id
     */
    private String UserId;

    private LocalDateTime AddedTime;

    /**
     * 日志类型为正常
     */
    @TableField(exist = false)
    public static final Boolean LogInfo = false;

    /**
     * 日志类型为异常
     */
    @TableField(exist = false)
    public static final Boolean LogError = true;
}
