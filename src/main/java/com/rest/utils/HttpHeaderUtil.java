package com.rest.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bruce.ge on 2016/12/7.
 */
public class HttpHeaderUtil {
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        return remoteAddr;
    }
}
