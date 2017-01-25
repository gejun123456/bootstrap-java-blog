package com.rest.vo;

import lombok.*;

import java.util.Date;

/**
 * @Author bruce.ge
 * @Date 2017/1/25
 * @Description
 */
@Getter
@Setter
@ToString
public class TagVo {
    private Integer id;

    private String tagName;

    private Date createTime;

    private Date updateTime;

}
