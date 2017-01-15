package com.rest.controller;

import com.rest.Request.RegisterRequest;
import com.rest.bean.User;
import com.rest.constant.SessionConstants;
import com.rest.domain.UserPO;
import com.rest.mapper.UserPODao;
import com.rest.response.BaseResponse;
import com.rest.response.CodeConstants;
import com.rest.response.CodeEnum;
import com.rest.response.MsgConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jodd.util.BCrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
@Slf4j
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

    @ApiOperation(value = "注册用户", response = BaseResponse.class, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/register")
    @ApiResponses({@ApiResponse(code = CodeConstants.username_already_exist, message = MsgConstants.username_already_exist),
            @ApiResponse(code = CodeConstants.validate_fail, message = MsgConstants.validate_fail),
    })
    @ResponseBody
    public BaseResponse register(@Valid @ModelAttribute RegisterRequest registerRequest, HttpSession session) {
        BaseResponse validateResponse = validate(registerRequest);
        if (validateResponse != null) return validateResponse;
        UserPO po = UserPO.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .cryptpasswod(BCrypt.hashpw(registerRequest.getPassword(), BCrypt.gensalt()))
                .build();
        po.setAuth(0);
        try {
            if (existUser == 0) {
                synchronized (this.getClass()) {
                    if (existUser == 0) {
                        //第一个创建的用户权限为admin
                        po.setAuth(1);
                        //只有插入成功了才行。
                        //这个阶段不可能出错。
                        userPODao.insert(po);
                        //add with session.
                        existUser = 1;
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
            BaseResponse validate = validate(registerRequest);
            if (validate != null) {
                return validate;
            }
            log.error("register catch exception, the userName is {}, the mobile is {}, the email is {}",
                    registerRequest.getUsername(), registerRequest.getMobile(), registerRequest.getEmail(), e);
            return BaseResponse.getFromCode(CodeEnum.validate_fail);
        }

        User sessionUser = User.builder().login(true).userName(po.getUsername()).userId(po.getId()).admin(po.getAuth() == 1).build();
        session.setAttribute(SessionConstants.USER, sessionUser);

        return BaseResponse.success("success");
    }

    private BaseResponse validate(RegisterRequest registerRequest) {
        List<UserPO> select =
                userPODao.findByUsername(registerRequest.getUsername());
        if (select.size() != 0) {
            return BaseResponse.getFromCode(CodeEnum.username_already_exist);
        }

        if (registerRequest.getMobile() != null) {
            List<UserPO> byMobile = userPODao.findByMobile(registerRequest.getMobile());
            if (byMobile.size() != 0) {
                return BaseResponse.getFromCode(CodeEnum.mobile_already_exist);
            }
        }

        if (registerRequest.getEmail() != null) {
            List<UserPO> byEmail = userPODao.findByEmail(registerRequest.getEmail());
            if (byEmail.size() != 0) {
                return BaseResponse.getFromCode(CodeEnum.email_already_exist);
            }
        }
        return null;
    }
}
