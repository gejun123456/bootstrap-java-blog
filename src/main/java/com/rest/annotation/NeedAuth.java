package com.rest.annotation;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by bruce.ge on 2016/11/13.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface NeedAuth {
    String redirectPage() default "/loginPage";
    @AliasFor("auth")
    AuthEnum value() default AuthEnum.LOGIN;

    //是否认证完成后要返回原来的页面
    boolean redirectBack() default false;
}
