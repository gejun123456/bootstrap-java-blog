package com.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author bruce.ge
 * @Date 2017/1/25
 * @Description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagPo {
    private Integer id;

    private String tagName;

    private Date createTime;

    private Date updateTime;

}
