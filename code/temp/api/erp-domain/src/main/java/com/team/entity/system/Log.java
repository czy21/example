package com.team.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.team.entity.BaseEntity;
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
public class Log extends BaseEntity<Log> {
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
