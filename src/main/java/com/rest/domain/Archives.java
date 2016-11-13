package com.rest.domain;

import java.util.Date;

/**
 * Created by bruce.ge on 2016/11/6.
 */
public class Archives {
    private Integer id;

    private Date addtime;

    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
