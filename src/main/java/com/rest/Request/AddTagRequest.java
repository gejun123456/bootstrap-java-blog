package com.rest.Request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Author bruce.ge
 * @Date 2017/1/25
 * @Description
 */
@Getter
@Setter
public class AddTagRequest {
    @NotEmpty
    private String name;
}
