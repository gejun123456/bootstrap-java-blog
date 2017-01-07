package com.rest.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by bruce.ge on 2016/11/18.
 */
@Data
public class CommentPO {
    private Integer id;
    private Integer article_id;
    private Integer reply_id;
    private String username;
    private String content;
    private Date addtime;
    private Date updatetime;
    private Short viewed;
    private String comment_ip;
}
