package com.rest.Request;

import lombok.Data;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotNull;

/**
 * Created by bruce.ge on 2016/11/18.
 */

@Data
public class ReplyCommentRequest {
    @NotNull
    public Integer replyCommentId;

    @NotNull
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String content;

    @NotNull
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String name;

    @NotNull
    public Integer articleId;
}
