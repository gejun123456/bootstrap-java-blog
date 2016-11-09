package com.rest.storage;

/**
 * Created by bruce.ge on 2016/11/9.
 */
public class StorageFileNotFoundException extends RuntimeException {
    public StorageFileNotFoundException(String message){
        super(message);
    }

    public StorageFileNotFoundException(String message,Throwable cause){
        super(message,cause);
    }
}
