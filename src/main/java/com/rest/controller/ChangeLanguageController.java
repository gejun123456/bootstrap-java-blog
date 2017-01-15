package com.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author bruce.ge
 * @Date 2017/1/15
 * @Description
 */
@Controller
public class ChangeLanguageController {

    @GetMapping("/changeLanguage")
    public String changeLanguage() {
        return "redirect:/";
    }
}
