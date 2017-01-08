package com.rest.controller.admin;

import com.rest.annotation.AuthEnum;
import com.rest.annotation.NeedAuth;
import org.springframework.stereotype.Controller;

/**
 * Created by bruce.ge on 2017/1/8.
 */
@Controller
@NeedAuth(AuthEnum.ADMIN)
public class EditAboutController {

}
