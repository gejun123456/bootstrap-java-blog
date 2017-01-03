package com.rest.Request;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by bruce.ge on 2016/11/6.
 */
@Setter
@Getter
public class AddContentRequest {
    private String title;

    private String sourceContent;
}
