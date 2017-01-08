package com.rest.response;

/**
 * Created by bruce.ge on 2017/1/9.
 */
public enum CodeEnum {
    success(CodeConstants.success, MsgConstants.success),
    user_already_exist(CodeConstants.user_already_exist, MsgConstants.user_already_exist),
    validate_fail(CodeConstants.validate_fail, MsgConstants.validate_fail);

    int code;
    String msg;

    CodeEnum(int value, String msg) {
        this.code = value;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }


}
