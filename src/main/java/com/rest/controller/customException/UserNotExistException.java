package com.rest.controller.customException;

import org.jetbrains.annotations.NonNls;

/**
 * @Author bruce.ge
 * @Date 2017/1/24
 * @Description
 */
public class UserNotExistException extends RuntimeException{
    public UserNotExistException() {
        super();
    }

    public UserNotExistException(@NonNls String message) {
        super(message);
    }
}
