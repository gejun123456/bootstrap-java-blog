package com.rest.storage;

/**
 * Created by bruce.ge on 2016/11/9.
 */
public class StorageExceptioin extends RuntimeException {
    public StorageExceptioin(String message){
        super(message);
    }

    public StorageExceptioin(String message,Throwable cause){
        super(message,cause);
    }
}
