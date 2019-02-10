package com.team.entity;

import com.baidu.fsg.uid.UidGenerator;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface PrimaryKeyGenerator {
    Class<? extends UidGenerator> keyGeneratorClass();
}
