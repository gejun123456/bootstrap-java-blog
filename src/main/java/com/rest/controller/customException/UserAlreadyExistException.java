package com.rest.controller.customException;

import org.jetbrains.annotations.NonNls;

/**
 * @Author bruce.ge
 * @Date 2017/1/23
 * @Description
 */
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(@NonNls String message) {
        super(message);
    }
}
