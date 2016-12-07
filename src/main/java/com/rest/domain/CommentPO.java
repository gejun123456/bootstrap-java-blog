package com.rest.domain;

import java.util.Date;

/**
 * Created by bruce.ge on 2016/11/18.
 */
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


    public Short getViewed() {
        return viewed;
    }

    public void setViewed(Short viewed) {
        this.viewed = viewed;
    }


    public String getComment_ip() {
        return comment_ip;
    }

    public void setComment_ip(String comment_ip) {
        this.comment_ip = comment_ip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public Integer getReply_id() {
        return reply_id;
    }

    public void setReply_id(Integer reply_id) {
        this.reply_id = reply_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
