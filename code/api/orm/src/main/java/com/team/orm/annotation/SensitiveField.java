package com.team.orm.annotation;

import java.lang.annotation.*;

/**
 * @author zhaoyu.chen
 * @date 2022/10/31 14:05
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface SensitiveField {
    boolean mask() default false;
}
