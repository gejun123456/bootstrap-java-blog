package com.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author bruce.ge
 * @Date 2017/1/9
 * @Description base response
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    private int code;

    private Object data;

    private String msg;

    public static BaseResponse getFromCode(CodeEnum codeEnum) {
        return BaseResponse.builder()
                .code(codeEnum.getCode())
                .msg(codeEnum.getMsg())
                .build();
    }


    public static BaseResponse success(Object data) {
        return BaseResponse.builder()
                .code(CodeEnum.success.getCode())
                .msg(CodeEnum.success.getMsg())
                .data(data)
                .build();
    }

}
