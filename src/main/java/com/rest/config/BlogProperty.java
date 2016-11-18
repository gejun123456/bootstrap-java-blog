package com.rest.config;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * Created by bruce.ge on 2016/11/18.
 */
@Component
public class BlogProperty {

    private static Logger logger = LoggerFactory.getLogger(BlogProperty.class);
    @Value("${blog.comment}")
    private boolean comment;

    @Value("${blog.i18n}")
    private String language;


    private Locale locale;

    @PostConstruct
    public void init() {
        String[] split = language.split("_");
        if (split.length < 2) {
            logger.error("blog propertis init not right, will set english as default");
            locale = Locale.US;
        } else {
            locale = new Locale(split[0], split[1]);
        }
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
