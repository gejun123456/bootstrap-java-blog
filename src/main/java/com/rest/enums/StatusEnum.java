package com.rest.enums;

/**
 * Created by bruce.ge on 2017/1/3.
 */
public enum StatusEnum {
    ACTIVE(10),
    INVALID(20);

    private int value;

    StatusEnum(int value) {
        this.value = value;
    }


    public int getValue() {
        return this.value;
    }
}
