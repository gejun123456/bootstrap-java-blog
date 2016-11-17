package com.rest.converter;

import com.rest.Request.CommentRequest;
import com.rest.domain.CommentPO;

import java.util.Date;

/**
 * Created by bruce.ge on 2016/11/18.
 */
public class CommentConvert {
    public static CommentPO createPo(CommentRequest commentRequest, int id) {
        CommentPO po = new CommentPO();
        po.setArticle_id(id);
        po.setAddtime(new Date());
        po.setContent(commentRequest.getContent());
        po.setUsername(commentRequest.getName());
        po.setUpdatetime(new Date());
        return po;
    }
}
