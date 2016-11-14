package com.rest.utils;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * Created by bruce.ge on 2016/11/14.
 */
@Component
public class StaticResourceDisposaber implements DisposableBean {
    private static Logger logger = LoggerFactory.getLogger(StaticResourceDisposaber.class);
    @Override
    public void destroy() throws Exception {
        logger.info("StaticResourceDisposaber destroy go on");
        MarkDownUtil.destroy();
    }
}
