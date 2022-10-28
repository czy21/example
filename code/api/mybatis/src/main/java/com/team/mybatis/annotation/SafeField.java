package com.team.mybatis.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface SafeField {

    String select() default ""; // decrypt

    String update() default ""; // encrypt
}
