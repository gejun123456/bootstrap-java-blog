package com.rest.Request;

import javax.validation.constraints.NotNull;

/**
 * Created by bruce.ge on 2016/11/18.
 */
public class ReplyCommentRequest {
    @NotNull
    public Integer replyCommentId;

    @NotNull
    public String content;

    @NotNull
    public String name;

    @NotNull
    public Integer articleId;


    public Integer getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(Integer replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "ReplyCommentRequest{" +
                "replyCommentId=" + replyCommentId +
                ", content='" + content + '\'' +
                ", name='" + name + '\'' +
                ", articleId=" + articleId +
                '}';
    }
}
