package com.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bruce.ge on 2016/11/20.
 */
@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
