package com.rest.dto;

/**
 * Created by bruce.ge on 2016/11/7.
 */
public class QueryResult {
    private int id;

    private String markText="";

    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarkText() {
        return markText;
    }

    public void setMarkText(String markText) {
        this.markText = markText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
