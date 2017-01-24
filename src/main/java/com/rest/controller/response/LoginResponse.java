package com.rest.controller.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author bruce.ge
 * @Date 2017/1/24
 * @Description
 */
@Getter
@Setter
public class LoginResponse {
    private String redirectUrl;

    public LoginResponse(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
