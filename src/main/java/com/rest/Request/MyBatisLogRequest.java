package com.rest.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author bruce.ge
 * @Date 2017/2/27
 * @Description
 */
@Getter
@Setter
@ToString
public class MyBatisLogRequest {
    private String className;

    private String messages;


    private String loggerLevel;

}
