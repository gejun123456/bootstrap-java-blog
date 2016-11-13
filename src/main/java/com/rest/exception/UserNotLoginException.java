package com.rest.exception;

/**
 * Created by bruce.ge on 2016/11/13.
 */
public class UserNotLoginException extends Exception{

    public UserNotLoginException(){
        super();
    }
    public UserNotLoginException(String message){
        super(message);
    }
}
