package com.team.mybatis.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SensitiveAction {
    SensitiveEnum selectParam() default SensitiveEnum.NONE;
    SensitiveEnum selectResult() default SensitiveEnum.DECRYPT;
    SensitiveEnum updateParam() default SensitiveEnum.ENCRYPT;
}
