package com.rest.controller;

import com.rest.annotation.ExecutionTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by bruce.ge on 2016/11/7.
 */
@Controller
public class AboutController {
    @GetMapping("/about")
    @ExecutionTime
    public String getAbout() {
        return "about";
    }
}
