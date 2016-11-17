package com.rest.vo;

/**
 * Created by bruce.ge on 2016/11/18.
 */
public class CommentVo {
    private String name;

    private String comment;

    private String ago;


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

    @Override
    public String toString() {
        return "CommentVo{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", ago='" + ago + '\'' +
                '}';
    }
}
