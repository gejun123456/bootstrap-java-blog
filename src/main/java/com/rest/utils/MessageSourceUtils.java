package com.rest.utils;

import com.rest.config.BlogProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

/**
 * Created by bruce.ge on 2016/11/18.
 */
@Component
public class MessageSourceUtils implements MessageSourceAware{
    private MessageSource messageSource;

    @Autowired
    private BlogProperty blogProperty;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    public String getMessage(String code){
        return messageSource.getMessage(code,new Object[]{},blogProperty.getLocale());
    }
}
