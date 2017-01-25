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
public class ContentTagRelation {
    private Integer id;

    private Integer contentId;

    private Integer tagId;

    private Date createTime;


    private Date updateTime;
}
