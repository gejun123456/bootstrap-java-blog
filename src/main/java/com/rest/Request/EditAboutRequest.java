package com.rest.Request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Author bruce.ge
 * @Date 2017/2/15
 * @Description
 */
@Getter
@Setter
public class EditAboutRequest {

    @NotEmpty
    private String sourceContent;

    @NotEmpty
    private String sourceHtml;
}
