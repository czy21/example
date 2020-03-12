package com.team.cooperated.annotation;

import com.team.cooperated.pocket.PocketProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SpecialPocket {
    Class<? extends PocketProvider<?>>[] value();
}