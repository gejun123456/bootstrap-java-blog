package com.rest.controller;

import com.google.common.collect.Lists;
import com.rest.dto.PageContentDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by bruce.ge on 2016/11/6.
 */
@RestController
public class PageListController {
    @RequestMapping("/getPage")
    //穿一个page参数
    public List<PageContentDto> getList(@RequestParam("page") int page){
        List<PageContentDto> dtos = Lists.newArrayList();
        if(page==1){
            PageContentDto dto = new PageContentDto();
            dto.setTitle("nimei");
            dto.setContent("hehe");
            dto.setLink("www.baidu.com");
            dto.setStartDate(new Date());
            dtos.add(dto);
        }
        return dtos;
    }
}
