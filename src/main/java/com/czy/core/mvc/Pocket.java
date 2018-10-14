package com.czy.core.mvc;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Pocket {
    Class[] entity() default Pocket.class;
}
