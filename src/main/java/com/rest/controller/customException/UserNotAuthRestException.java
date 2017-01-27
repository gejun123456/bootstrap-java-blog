package com.rest.controller.customException;

import org.jetbrains.annotations.NonNls;

/**
 * @Author bruce.ge
 * @Date 2017/1/27
 * @Description
 */
public class UserNotAuthRestException extends RuntimeException{
    public UserNotAuthRestException() {
        super();
    }

    public UserNotAuthRestException(@NonNls String message) {
        super(message);
    }
}
