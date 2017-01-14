package com.rest.Request;

import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by bruce.ge on 2016/11/14.
 */
public class RegisterRequest {

    @NotEmpty
    @ApiParam(value = "用户名", required = true)
    private String username;

    @NotEmpty
    @ApiParam(value = "密码", required = true)
    private String password;

    @Email
    @Size(min = 2)
    @ApiParam(value = "邮箱,最小长度2", required = true)
    private String email;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
