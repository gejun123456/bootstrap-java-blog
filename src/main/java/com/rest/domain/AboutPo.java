package com.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by bruce.ge on 2017/1/8.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AboutPo {

    private Integer id;
    //use markdown to diplay all the info for it.
    private String sourceContent;


    private String markdownHtml;

    private Date createTime;

    private Date updateTime;

    private Integer userId;
}
