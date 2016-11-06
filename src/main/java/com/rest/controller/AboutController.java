package com.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bruce.ge on 2016/11/7.
 */
@Controller
public class AboutController {
    @RequestMapping("/about")
    public String getAbout(){
        return "about";
    }
}
