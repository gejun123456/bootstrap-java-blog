package com.rest.vo;

import java.util.Date;

/**
 * Created by bruce.ge on 2016/11/6.
 */
public class PageContentVo {

//文章的内容
    private String title;

    private String content;

    private String link;

    private String startDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
