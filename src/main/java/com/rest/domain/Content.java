package com.rest.domain;

import java.util.Date;

/**
 * Created by bruce.ge on 2016/11/6.
 */
public class Content {
    private Integer id;

    private String title;

    private String source_content;

    private String html_content;

    private Date updatetime;

    private Date addtime;

    private String index_content;


//    the use who wrote the article
    private String userId;

    //the article status.
    private Integer status;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource_content() {
        return source_content;
    }

    public void setSource_content(String source_content) {
        this.source_content = source_content;
    }

    public String getHtml_content() {
        return html_content;
    }

    public void setHtml_content(String html_content) {
        this.html_content = html_content;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }


    public String getIndex_content() {
        return index_content;
    }

    public void setIndex_content(String index_content) {
        this.index_content = index_content;
    }
}
