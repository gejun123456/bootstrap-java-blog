package com.rest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by bruce.ge on 2016/11/18.
 */
@Component
public class BlogProperty {

    private static Logger logger = LoggerFactory.getLogger(BlogProperty.class);
    @Value("${blog.comment}")
    private boolean comment;


    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }
}

