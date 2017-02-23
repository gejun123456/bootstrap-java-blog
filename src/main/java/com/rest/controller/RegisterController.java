package com.rest.controller;

import com.rest.Request.RegisterRequest;
import com.rest.bean.User;
import com.rest.constant.SessionConstants;
import com.rest.controller.customException.UserAlreadyExistException;
import com.rest.controller.errors.ErrorConstants;
import com.rest.controller.errors.ErrorVM;
import com.rest.domain.UserPO;
import com.rest.mapper.UserPODao;
import com.rest.response.MsgConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jodd.util.BCrypt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by bruce.ge on 2016/11/14.
 */
@Controller
@Api(value = "用户注册服务")
@Slf4j
public class RegisterController {
    @Autowired
    private UserPODao userPODao;

    @ApiOperation(value = "注册用户", response = ResponseEntity.class, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(path = "/register", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ResponseBody
    public ResponseEntity<?> register(@Valid @ModelAttribute RegisterRequest registerRequest, HttpSession session) {
        validate(registerRequest);
        UserPO po = UserPO.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .cryptpasswod(BCrypt.hashpw(registerRequest.getPassword(), BCrypt.gensalt()))
                .build();
        po.setAuth(0);
        int count = userPODao.getCount();
        try {
            if (count == 0) {
                synchronized (this.getClass()) {
                    if (count == 0) {
                        //第一个创建的用户权限为admin
                        po.setAuth(1);
                        //只有插入成功了才行。
                        //这个阶段不可能出错。
                        userPODao.insert(po);
                        //add with session.
                    } else {
                        //可能出出错
                        userPODao.insert(po);
                    }
                }
            } else {
                //could throw when unque exception
                userPODao.insert(po);
            }
        } catch (Exception e) {
            log.info("may exist new user that has the same userName or mobile or email", e);
            //try to recover the reason
            validate(registerRequest);
            log.error("register catch exception, the userName is {}, the mobile is {}, the email is {}",
                    registerRequest.getUsername(), registerRequest.getMobile(), registerRequest.getEmail(), e);
            throw e;
        }

        User sessionUser = User.builder().login(true).userName(po.getUsername()).userId(po.getId()).admin(po.getAuth() == 1).build();
        session.setAttribute(SessionConstants.USER, sessionUser);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private void validate(RegisterRequest registerRequest) {
        userPODao.findFirstByUsername(registerRequest.getUsername()).ifPresent(userPO -> {
            throw new UserAlreadyExistException(MsgConstants.username_already_exist);
        });
        if (StringUtils.isNotBlank(registerRequest.getMobile())) {
            userPODao.findFirstByMobile(registerRequest.getMobile()).ifPresent(userPO -> {
                throw new UserAlreadyExistException(MsgConstants.mobile_already_exist);
            });
        }
        userPODao.findFirstByEmail(registerRequest.getEmail()).ifPresent(userPO -> {
            throw new UserAlreadyExistException(MsgConstants.email_already_exist);
        });
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorVM handleWithAlreadyExist(UserAlreadyExistException ex) {
        return new ErrorVM(ErrorConstants.ERR_ALREADYEXIST, ex.getMessage());
    }
}
