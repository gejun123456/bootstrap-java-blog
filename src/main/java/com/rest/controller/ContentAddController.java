package com.rest.controller;

import com.rest.Request.AddContentRequest;
import com.rest.converter.ContentConverter;
import com.rest.domain.Content;
import com.rest.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bruce.ge on 2016/11/6.
 */
@Controller
public class ContentAddController {
    @Autowired
    private ContentMapper contentMapper;
    @RequestMapping("/addContent")
    @ResponseBody
    public boolean addContent(AddContentRequest request){
        //which shall redirect when ok.
        System.out.println(request.getTitle());
        System.out.println(request.getSourceContent());
        Content content = ContentConverter.convertToContent(request);
        contentMapper.addContent(content);
        return true;
    }

    @RequestMapping("/add")
    public String addPage(){
        return "add";
    }
}
