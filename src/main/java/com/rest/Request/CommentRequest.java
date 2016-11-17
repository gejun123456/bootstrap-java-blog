package com.rest.Request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by bruce.ge on 2016/11/18.
 */
public class CommentRequest {

    @NotNull
    private String name;

    @NotNull
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentRequest{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
