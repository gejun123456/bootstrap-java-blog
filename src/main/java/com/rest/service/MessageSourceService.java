package com.rest.service;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by bruce.ge on 2016/11/18.
 */
@Component
public class MessageSourceService implements MessageSourceAware {
    private MessageSource messageSource;


    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    public String getMessage(String code) {
        return messageSource.getMessage(code, new Object[]{}, LocaleContextHolder.getLocale());
    }
}
