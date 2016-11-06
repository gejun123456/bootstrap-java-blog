package com.rest.controller;

import com.rest.Request.AddContentRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bruce.ge on 2016/11/6.
 */
@Controller
public class ContentAddController {
    @RequestMapping("/addContent")
    @ResponseBody
    public boolean addContent(AddContentRequest request){
        //which shall redirect when ok.
        return true;
    }

    @RequestMapping("/add")
    public String addPage(){
        return "add";
    }
}
