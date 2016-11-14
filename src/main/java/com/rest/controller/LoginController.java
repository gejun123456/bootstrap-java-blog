package com.rest.controller;


import com.rest.constant.CookieConstants;
import com.rest.constant.SessionConstants;
import com.rest.converter.UserConverter;
import com.rest.domain.UserPO;
import com.rest.service.LoginService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private LoginService loginService;
    @RequestMapping("/login")
    // should check wiht data. //get some common validator. for basic login. try to add some.
    public String login(HttpSession session, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password")String password,
                        @RequestParam(value = "remember",required = false) String remember){
        UserPO login = loginService.login(username, password);
        if(login!=null){
            session.setAttribute(SessionConstants.USER, UserConverter.convertToUser(login));
            //try to add cookie.
            //try to set cookie by database. update database when new cookie is generated.
            if(remember!=null) {
                Cookie cookie = new Cookie(CookieConstants.USERNAME, username);
                //save for 7 day.
                cookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(cookie);
                String random = RandomStringUtils.random(33);
                Cookie pass = new Cookie(CookieConstants.PASSWORD, random);
                //then go to update the database with the cookie vlaue.
                loginService.updateCookie(random,login.getId());
                //save for 100 day.
                pass.setMaxAge(60 * 60 * 24 *7);
                response.addCookie(pass);
            }
//            can add in session.
            Object attribute = session.getAttribute(SessionConstants.AUTHBACKPAGE);
            if(attribute !=null) {
                String back = (String) attribute;
                session.removeAttribute(SessionConstants.AUTHBACKPAGE);
                return "redirect:" + back;
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/loginPage?fail=1";
        }
    }


    @RequestMapping("/loginPage")
    public String loginPage(ModelMap modelMap,@RequestParam(value = "fail",required = false) String fail){
        if(StringUtils.isNotBlank(fail)) {
            modelMap.addAttribute("logindd", "login fail, please input right message");
         }
        return "login";
    }
}
