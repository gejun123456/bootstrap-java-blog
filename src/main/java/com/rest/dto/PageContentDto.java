package com.rest.dto;

import java.util.Date;

/**
 * Created by bruce.ge on 2016/11/6.
 */
public class PageContentDto {

//文章的内容
    private String title;

    private String content;

    private String link;

    private Date startDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
