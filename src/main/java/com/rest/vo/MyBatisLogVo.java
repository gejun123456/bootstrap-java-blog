package com.rest.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author bruce.ge
 * @Date 2017/2/27
 * @Description
 */
@Getter
@Setter
public class MyBatisLogVo {
    private Integer id;

    private String className;

    private String userIp;

    private String messages;

    private String createTime;

    private String loggerLevel;
}
