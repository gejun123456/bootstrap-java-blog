package com.rest.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.rest.Request.MyBatisLogRequest;
import com.rest.controller.response.MyBatisLogResponse;
import com.rest.domain.MyBatisLog;
import com.rest.mapper.MyBatisLogDao;
import com.rest.utils.IpUtils;
import com.rest.vo.MyBatisLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author bruce.ge
 * @Date 2017/2/27
 * @Description
 */
@RestController
@RequestMapping("/mybatislog")
public class MyBatisLoggerController {

    @Autowired
    private MyBatisLogDao myBatisLogDao;

    @PostMapping("add")
    public MyBatisLogResponse logger(@RequestBody MyBatisLogRequest logRequest, HttpServletRequest request) {
        String userIp = IpUtils.getUserIp(request);
        MyBatisLog log = convertTo(logRequest, userIp);
        myBatisLogDao.insert(log);
        MyBatisLogResponse response = new MyBatisLogResponse();
        response.setSuccess(true);
        return response;
    }

    private MyBatisLog convertTo(MyBatisLogRequest logRequest, String userIp) {
        MyBatisLog myBatisLog = new MyBatisLog();
        myBatisLog.setClassName(logRequest.getClassName());
        myBatisLog.setUserIp(userIp);
        myBatisLog.setMessages(logRequest.getMessages());
        myBatisLog.setCreateTime(new Date());
        myBatisLog.setLoggerLevel(logRequest.getLoggerLevel());
        return myBatisLog;
    }


    @GetMapping("view/{page}")
    public ModelAndView view(@PathVariable(required = false) Integer page) {
        if (page == null) {
            page = 1;
        }
        ModelAndView viewPage = new ModelAndView("mybatisLogView");
        PageHelper.startPage(page, 50);
        List<MyBatisLog> myBatisPo =
            myBatisLogDao.find();
        List<MyBatisLogVo> myBatisLogs = convertToVo(myBatisPo);
        Page logPages = (Page) myBatisPo;
        int pages = logPages.getPages();
        if (page > pages || page <= 0) {
            viewPage.addObject("contents", myBatisLogs);
            return viewPage;
        }
        viewPage.addObject("contents", myBatisLogs);
        int prevous = page - 1;
        int next = page + 1;
        if (prevous > 0) {
            viewPage.addObject("previousLink", "/mybatislog/view/" + prevous);
        }
        if (next <= pages) {
            viewPage.addObject("nextLink", "/mybatislog/view/" + next);
        }
        return viewPage;
    }

    private List<MyBatisLogVo> convertToVo(List<MyBatisLog> myBatisPo) {
        List<MyBatisLogVo> myBatisLogVolist = Lists.newArrayList();
        for (MyBatisLog log : myBatisPo) {
            myBatisLogVolist.add(convert(log));
        }
        return myBatisLogVolist;
    }

    private MyBatisLogVo convert(MyBatisLog log) {
        MyBatisLogVo myBatisLogVo = new MyBatisLogVo();
        myBatisLogVo.setId(log.getId());
        myBatisLogVo.setClassName(log.getClassName());
        myBatisLogVo.setUserIp(log.getUserIp());
        myBatisLogVo.setMessages(log.getMessages());
        myBatisLogVo.setCreateTime(new SimpleDateFormat("yyyy MM dd HH:mm:ss").format(log.getCreateTime()));
        myBatisLogVo.setLoggerLevel(log.getLoggerLevel());
        return myBatisLogVo;
    }
}
