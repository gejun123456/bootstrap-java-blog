package com.rest.controller.customException;

/**
 * @Author bruce.ge
 * @Date 2017/1/20
 * @Description
 */
public class TransactionException extends RuntimeException{
    public TransactionException(Throwable e) {
        super(e);
    }


    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
