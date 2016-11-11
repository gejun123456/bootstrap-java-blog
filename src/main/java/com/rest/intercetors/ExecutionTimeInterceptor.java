package com.rest.intercetors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bruce.ge on 2016/11/10.
 */
public class ExecutionTimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        httpServletRequest.setAttribute("startTime",System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        long endTime = System.currentTimeMillis();
        //the controller might riderect so there is nothing.
        if(httpServletRequest.getAttribute("startTime")!=null){
            long useTime = endTime - (Long) httpServletRequest.getAttribute("startTime");
            //ajax request has no modelAndView
            if(modelAndView!=null) {
                modelAndView.addObject("useTime", useTime);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
