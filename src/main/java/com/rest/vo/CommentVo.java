package com.rest.vo;

import java.util.Date;

/**
 * Created by bruce.ge on 2016/11/18.
 */
public class CommentVo {
    private String name;

    private String comment;

    private String ago;

    private Integer id;

    private Integer parentId;

    private Date addtime;

//    if viewed than is 1 else is 0.
    private Integer viewed;


    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getAgo() {
        return ago;
    }

    public void setAgo(String ago) {
        this.ago = ago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

}
