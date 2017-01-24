package com.rest.controller.errors;

import java.io.Serializable;

public class FieldErrorVM implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String objectName;

    private final String field;

    private final String message;

    private final String defaultMessage;

    public FieldErrorVM(String dto, String field, String message,String defaultMessage) {
        this.objectName = dto;
        this.field = field;
        this.message = message;
        this.defaultMessage = defaultMessage;
    }


    public String getDefaultMessage() {
        return defaultMessage;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

}
