package com.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * Created by bruce.ge on 2016/11/18.
 */
@Component
public class BlogProperty {
    @Value("${blog.comment}")
    private boolean comment;

    @Value("${blog.i18n}")
    private String language;


    private Locale locale;

    @PostConstruct
    public void init(){
        String[] split = language.split("_");
        locale = new Locale(split[0],split[1]);
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
