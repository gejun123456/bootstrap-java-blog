package com.rest.annotation;

import java.lang.annotation.*;

/**
 * Created by bruce.ge on 2017/1/8.
 */
@Retention(RetentionPolicy.RUNTIME)
// TODO: 2017/1/8 consider if i shall support with type
@Target({ElementType.METHOD})
@Inherited
@Documented
public @interface ExecutionTime {

    boolean logToDatabase() default false;
    /*log args when time are over the time unit is ms*/
    int logArgsTime() default 1000;

    boolean onlyLogOverTime() default false;

    int logOverTime() default 1000;
}
