package com.team.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.team.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Value;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * @author 陈昭宇
 * @description 系统日志表
 * @date 2018-12-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Log extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    private String logId;
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
     * 请求的用户id
     */
    private String userId;
    /**
     * 请求时长
     */
    private Integer spendTime;

    @TableField(exist = false)
    private String operatorName;
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
