package com.rest.controller.customException;

import org.jetbrains.annotations.NonNls;

/**
 * @Author bruce.ge
 * @Date 2017/1/20
 * @Description
 */
public class UserSessionTimeOutException extends RuntimeException{
    public UserSessionTimeOutException() {
        super();
    }

    public UserSessionTimeOutException(@NonNls String message) {
        super(message);
    }
}
