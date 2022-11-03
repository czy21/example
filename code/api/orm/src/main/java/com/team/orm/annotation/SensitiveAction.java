package com.team.orm.annotation;


import java.lang.annotation.*;

/**
 * @author zhaoyu.chen
 * @date 2022/10/31 14:05
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SensitiveAction {
    SensitiveEnum selectParam() default SensitiveEnum.NONE;

    SensitiveEnum selectResult() default SensitiveEnum.DECRYPT;

    SensitiveEnum updateParam() default SensitiveEnum.ENCRYPT;
}
