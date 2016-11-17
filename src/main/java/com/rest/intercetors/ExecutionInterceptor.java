package com.rest.intercetors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bruce.ge on 2016/11/10.
 */
public class ExecutionInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger("access");
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        httpServletRequest.setAttribute("startTime",System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String remoteAddr = httpServletRequest.getHeader("X-Forwarded-For");
        String requestURI = httpServletRequest.getRequestURI();
        long endTime = System.currentTimeMillis();
        //the controller might riderect so there is nothing.
        if(httpServletRequest.getAttribute("startTime")!=null){
            long useTime = endTime - (Long) httpServletRequest.getAttribute("startTime");
            //ajax request has no modelAndView
            logger.info("the client ip is {} the requestURI is {},the use time is {}", remoteAddr, requestURI,useTime);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
