package com.rest.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author bruce.ge
 * @Date 2017/2/27
 * @Description
 */
@Getter
@Setter
public class MyBatisLog {

    private Integer id;

    private String className;

    private String userIp;

    private String messages;

    private Date createTime;

    private String loggerLevel;
}
