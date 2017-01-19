package com.rest.Request;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by bruce.ge on 2016/11/14.
 */
@Data
public class RegisterRequest {

    @NotEmpty
    @ApiParam(value = "用户名", required = true)
    private String username;

    @NotEmpty
    @ApiParam(value = "密码", required = true)
    private String password;

    @Email
    @Size(min = 5)
    @ApiParam(value = "邮箱,最小长度5", required = true)
    private String email;

    private String mobile;
}
