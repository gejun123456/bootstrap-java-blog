package com.rest.controller.admin;

import com.rest.annotation.AuthEnum;
import com.rest.annotation.NeedAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bruce.ge on 2017/1/8.
 */
@Controller
@NeedAuth(AuthEnum.ADMIN)
public class AdminController {
    @GetMapping("/admin")
    public ModelAndView admin() {
        ModelAndView adminView = new ModelAndView("admin");
        return adminView;
    }
}
