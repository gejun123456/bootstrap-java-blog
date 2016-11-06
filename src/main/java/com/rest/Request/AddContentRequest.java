package com.rest.Request;

/**
 * Created by bruce.ge on 2016/11/6.
 */
public class AddContentRequest {
    private String title;

    private String sourceContent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSourceContent() {
        return sourceContent;
    }

    public void setSourceContent(String sourceContent) {
        this.sourceContent = sourceContent;
    }
}
