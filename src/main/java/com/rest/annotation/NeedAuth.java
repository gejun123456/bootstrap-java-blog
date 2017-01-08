package com.rest.annotation;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * Created by bruce.ge on 2016/11/13.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Documented
public @interface NeedAuth {
    String redirectPage() default "/loginPage";

    @AliasFor("auth")
    AuthEnum value() default AuthEnum.LOGIN;

    @AliasFor("value")
    AuthEnum auth() default AuthEnum.LOGIN;

    //是否认证完成后要返回原来的页面
    boolean redirectBack() default false;
}
