package com.rest.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by bruce.ge on 2016/11/8.
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    // should check wiht data. //get some common validator. for basic login. try to add some.
    public String login(HttpSession session, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password")String password){
        if(username.equals("bruce")&&password.equals("123")){
            session.setAttribute("login",true);
            Cookie cookie = new Cookie("logininfo", "aabbcc");
            //save for 100 day.
            cookie.setMaxAge(60*60*24*100);
            response.addCookie(cookie);
            return "redirect:/add";
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
