package com.rest.controller;

import com.rest.Request.RegisterRequest;
import com.rest.bean.User;
import com.rest.constant.SessionConstants;
import com.rest.domain.UserPO;
import com.rest.mapper.UserPODao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jodd.util.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by bruce.ge on 2016/11/14.
 */
@Controller
@Api(value = "用户注册服务")
public class RegisterController {
    @Autowired
    private UserPODao userPODao;

    private static int existUser = 0;

    @PostConstruct
    public void init() {
        //go to check the talbe contains content.
        int count = userPODao.getCount();
        if (count > 0) {
            existUser = 1;
        }
    }

    @ApiOperation("注册用户")
    @PostMapping("/register")
    @ApiResponses({@ApiResponse(code = 200, message = "success"), @ApiResponse(code = 555, message = "fail")})
    @ResponseBody
    public String register(@Valid @ModelAttribute RegisterRequest registerRequest, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "check failed, please reinput";
        }
        List<UserPO> select =
                userPODao.findByUsername(registerRequest.getUsername());
        if (select.size() != 0) {
            return "user exist, please input other name";
        } else {
            UserPO po = new UserPO();
            po.setUsername(registerRequest.getUsername());
            po.setEmail(registerRequest.getEmail());
            po.setCryptpasswod(BCrypt.hashpw(registerRequest.getPassword(), BCrypt.gensalt()));
            int insert = 0;
            po.setAuth(0);
            if (existUser == 0) {
                synchronized (this.getClass()) {
                    if (existUser == 0) {
                        //第一个创建的用户权限为admin
                        po.setAuth(1);
                        //只有插入成功了才行。
                        //这个阶段不可能出错。
                        insert = userPODao.insert(po);
                        //add with session.
                        existUser = 1;
                    } else {
                        //可能出出错
                        insert = userPODao.insert(po);
                    }
                }
            } else {
                //可能会出错
                insert = userPODao.insert(po);
            }
            if (insert == 1) {
                User sessionUser = User.builder().login(true).userName(po.getUsername()).userId(po.getId()).admin(po.getAuth() == 1).build();
                session.setAttribute(SessionConstants.USER, sessionUser);
            } else {
                return "user exist, please input other name";
            }

            return "success";
        }
    }
}
