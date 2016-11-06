package com.rest.controller;

import com.google.common.collect.Lists;
import com.rest.converter.ContentConverter;
import com.rest.domain.Content;
import com.rest.dto.PageContentDto;
import com.rest.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ContentMapper contentMapper;
    @RequestMapping("/getPage")
    //穿一个page参数
    public List<PageContentDto> getList(@RequestParam("page") int page,@RequestParam(value = "pagesize",defaultValue = "10") int pageSize){
        List<PageContentDto> dtos = Lists.newArrayList();
        int start = (page-1)*pageSize;
        int limit = pageSize;
        List<Content> pages = contentMapper.getPage(start, limit);
        List<PageContentDto> pageContentDtos = ContentConverter.convetToPageDto(pages);
        return pageContentDtos;
    }

    @RequestMapping("/getpagecount")
    public int getPageCount(@RequestParam(value = "pagesize",defaultValue = "10") int pageSize){
        int count = contentMapper.getCount();
        return count/pageSize;
    }

    // TODO: 2016/11/6 shall add with archived. need to refacter to mutiple page.
    @RequestMapping("/getarchives")
    public String getArchives(){
        return "archives";
    }
}
