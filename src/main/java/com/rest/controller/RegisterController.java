package com.rest.controller;

import com.rest.Request.RegisterRequest;
import com.rest.bean.UserBuilder;
import com.rest.constant.SessionConstants;
import com.rest.domain.UserPO;
import com.rest.service.UserPOService;
import jodd.util.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by bruce.ge on 2016/11/14.
 */
@Controller
public class RegisterController {
    @Autowired
    private UserPOService userPOService;
    @RequestMapping("/register")
    @ResponseBody
    public String register(@Valid RegisterRequest registerRequest, BindingResult bindingResult, HttpSession session){
        if(bindingResult.hasErrors()){
            return "check failed, please reinput";
        }
        UserPO query = new UserPO();
        query.setUsername(registerRequest.getUsername());
        List<UserPO> select =
                userPOService.select(query);
        if(select.size()!=0){
            return "user exist, please input other name";
        } else {
            UserPO po = new UserPO();
            po.setUsername(registerRequest.getUsername());
            po.setEmail(registerRequest.getEmail());
            po.setCryptpasswod(BCrypt.hashpw(registerRequest.getPassword(),BCrypt.gensalt()));
            int insert = userPOService.insert(po);
            if(insert==1){
                //add with session.
                UserBuilder userBuilder = UserBuilder.anUser().
                        withLogin(true).withUserName(po.getUsername()).withUserId(po.getId());
                if(po.getUsername().equals("bruce")){
                    session.setAttribute(SessionConstants.USER, userBuilder.withAdmin(true).build());
                } else {
                    session.setAttribute(SessionConstants.USER, userBuilder.withAdmin(false).build());
                }
            }
            return "success";
        }
    }
}
