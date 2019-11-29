package com.team.application.core.annotations;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Pockets.class)
public @interface Pocket {
    Class entity();

    boolean obtainTree() default false;
}
