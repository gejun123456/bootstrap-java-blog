package com.rest.controller;


import com.rest.Request.LoginRequest;
import com.rest.constant.CookieConstants;
import com.rest.constant.SessionConstants;
import com.rest.controller.customException.UserNotExistException;
import com.rest.controller.errors.ErrorConstants;
import com.rest.controller.errors.ErrorVM;
import com.rest.controller.response.LoginResponse;
import com.rest.converter.UserConverter;
import com.rest.response.MsgConstants;
import com.rest.service.LoginService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by bruce.ge on 2016/11/8.
 */
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(path = "/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public LoginResponse login(HttpSession session, HttpServletResponse response, @Valid LoginRequest request
    ) {
        return loginService.login(request.getUsername(), request.getPassword())
            .map((login) -> {
                session.setAttribute(SessionConstants.USER, UserConverter.convertToUser(login));
                //try to add cookie.
                //try to set cookie by database. update database when new cookie is generated.
                if (request.getRemember() != null) {
                    Cookie cookie = new Cookie(CookieConstants.USERNAME, request.getUsername());
                    //save for 7 day.
                    cookie.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(cookie);
                    String random = RandomStringUtils.random(33, true, true);
                    Cookie pass = new Cookie(CookieConstants.PASSWORD, random);
                    //then go to update the database with the cookie vlaue.
                    loginService.updateCookie(random, login.getId());
                    //save for 100 day.
                    pass.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(pass);
                }
//            can add in session.
                Object attribute = session.getAttribute(SessionConstants.AUTHBACKPAGE);
                if (attribute != null) {
                    String back = (String) attribute;
                    session.removeAttribute(SessionConstants.AUTHBACKPAGE);
                    return new LoginResponse(back);
                } else {
                    return new LoginResponse("/");
                }
            }).orElseThrow(() -> new UserNotExistException());
    }


    @GetMapping("/loginPage")
    public String loginPage(ModelMap modelMap, @RequestParam(value = "fail", required = false) String fail) {
        return "login";
    }


    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorVM handleWithUserNotFound(UserNotExistException e) {
        return new ErrorVM(ErrorConstants.ERR_USERNOTEXIST, MsgConstants.user_not_eixst);
    }
}
