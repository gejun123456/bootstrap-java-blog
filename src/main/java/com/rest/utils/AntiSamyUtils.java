package com.rest.utils;

import org.owasp.validator.html.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * Created by bruce.ge on 2016/11/20.
 */
public class AntiSamyUtils {

    private static Logger logger = LoggerFactory.getLogger(AntiSamyUtils.class);

    private static AntiSamy antiSamy;

    public static String getCleanHtml(String sourceContent) {
        try {
            if (antiSamy == null) {
                synchronized (AntiSamyUtils.class) {
                    if (antiSamy == null) {
                        Policy policy = null;
                        policy = Policy.getInstance(AntiSamyUtils.class.getClassLoader().getResourceAsStream("antisamy-myspace-1.4.4.xml"));
                        antiSamy = new AntiSamy(policy);
                    }
                }
            }
            CleanResults scan = antiSamy.scan(sourceContent);
            return scan.getCleanHTML();
        }catch (Exception e){
            logger.error("antisamy get clean html catch exception, the sourceContent is {}",sourceContent,e);
            return "";
        }
    }
}
