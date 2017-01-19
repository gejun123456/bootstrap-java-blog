package com.rest.controller.customException;

import org.jetbrains.annotations.NonNls;

/**
 * @Author bruce.ge
 * @Date 2017/1/20
 * @Description
 */
public class RegisterValidateException extends RuntimeException {
    public RegisterValidateException() {
        super();
    }

    public RegisterValidateException(@NonNls String message) {
        super(message);
    }
}
