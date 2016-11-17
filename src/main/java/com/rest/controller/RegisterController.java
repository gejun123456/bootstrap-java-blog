package com.rest.controller;

import com.rest.Request.RegisterRequest;
import com.rest.bean.User;
import com.rest.bean.UserBuilder;
import com.rest.constant.SessionConstants;
import com.rest.domain.UserPO;
import com.rest.mapper.UserPODao;
import jodd.util.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by bruce.ge on 2016/11/14.
 */
@Controller
public class RegisterController {
    @Autowired
    private UserPODao userPODao;

    private static int tableExist = 0;

    @PostConstruct
    public void init() {
        //go to check the talbe contains content.
        int count = userPODao.getCount();
        if (count > 0) {
            tableExist = 1;
        }
    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(@Valid RegisterRequest registerRequest, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "check failed, please reinput";
        }
        UserPO query = new UserPO();
        query.setUsername(registerRequest.getUsername());
        List<UserPO> select =
                userPODao.select(query);
        if (select.size() != 0) {
            return "user exist, please input other name";
        } else {
            UserPO po = new UserPO();
            po.setUsername(registerRequest.getUsername());
            po.setEmail(registerRequest.getEmail());
            po.setCryptpasswod(BCrypt.hashpw(registerRequest.getPassword(), BCrypt.gensalt()));
            int insert = 0;
            po.setAuth(0);
            if (tableExist == 0) {
                synchronized (this.getClass()) {
                    if (tableExist == 0) {
                        //第一个创建的用户权限为admin
                        po.setAuth(1);
                        //只有插入成功了才行。
                        //这个阶段不可能出错。
                        insert = userPODao.insert(po);
                        //add with session.
                        tableExist = 1;
                    } else {
                        //可能出出错
                        insert = userPODao.insert(po);
                    }
                }
            } else {
                //可能会出错
                insert= userPODao.insert(po);
            }

            if (insert == 1) {
                User user = UserBuilder.anUser().
                        withLogin(true).withUserName(po.getUsername()).withUserId(po.getId()).withAdmin(po.getAuth() == 1).build();
                session.setAttribute(SessionConstants.USER, user);
            } else {
                return "user exist, please input other name";
            }

            return "success";
        }
    }
}
