package com.rest.controller;


import com.rest.bean.UserBuilder;
import com.rest.constant.CookieConstants;
import com.rest.constant.SessionConstants;
import com.rest.converter.UserConverter;
import com.rest.dto.UserDto;
import com.rest.service.LoginService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by bruce.ge on 2016/11/8.
 */
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @RequestMapping("/login")
    // should check wiht data. //get some common validator. for basic login. try to add some.
    public String login(HttpSession session, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password")String password,
                        @RequestParam(value = "backPage",defaultValue = "/")String backPage){
        UserDto login = loginService.login(username, password);
        if(login!=null){
            session.setAttribute(SessionConstants.USER, UserConverter.convertToUser(login));
            Cookie cookie = new Cookie(CookieConstants.USERNAME, username);
            //save for 100 day.
            cookie.setMaxAge(60*60*24*99);
            response.addCookie(cookie);
            Cookie pass = new Cookie(CookieConstants.PASSWORD, password);
            //save for 100 day.
            pass.setMaxAge(60*60*24*99);
            response.addCookie(pass);
            return "redirect:"+ backPage;
        } else {
            return "redirect:/loginPage?fail=1";
        }
    }


    @RequestMapping("/loginPage")
    public String loginPage(ModelMap modelMap,@RequestParam(value = "fail",required = false) String fail){
        if(StringUtils.isNotBlank(fail)) {
            modelMap.addAttribute("logindd", "failed");
         }
        return "login";
    }
}
