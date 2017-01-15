package com.rest.response;

/**
 * Created by bruce.ge on 2017/1/9.
 */
public enum CodeEnum {
    success(CodeConstants.success, MsgConstants.success),
    username_already_exist(CodeConstants.username_already_exist, MsgConstants.username_already_exist),
    mobile_already_exist(CodeConstants.mobile_already_exist, MsgConstants.mobile_already_exist),
    email_already_exist(CodeConstants.email_already_exist, MsgConstants.email_already_exist),
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
