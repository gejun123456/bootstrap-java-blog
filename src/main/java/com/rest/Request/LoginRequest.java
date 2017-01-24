package com.rest.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Author bruce.ge
 * @Date 2017/1/24
 * @Description
 */
@Getter
@Setter
@ToString
public class LoginRequest {
    @NotEmpty
    @Length(max = 50)
    private String username;

    @NotEmpty
    @Length(max = 50)
    private String password;

    private Boolean remember;
}
