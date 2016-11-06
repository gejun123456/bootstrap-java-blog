package com.rest.controller;

import com.google.common.collect.Lists;
import com.rest.converter.ContentConverter;
import com.rest.domain.Content;
import com.rest.dto.PageContentVo;
import com.rest.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    public List<PageContentVo> getList(@RequestParam("page") int page, @RequestParam(value = "pagesize",defaultValue = "10") int pageSize){
        int start = (page-1)*pageSize;
        int limit = pageSize;
        List<Content> pages = contentMapper.getPage(start, limit);
        List<PageContentVo> pageContentVos = ContentConverter.convetToPageDto(pages);
        return pageContentVos;
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

    @RequestMapping("/page/{pageNo}")
    public ModelAndView getPage(@PathVariable("pageNo") int pageNo){
        List<PageContentVo> pageContentVos = Lists.newArrayList();
        int pageSize =5;
        int count = contentMapper.getCount();
        int totalPagecount=(count-1)/pageSize+1;
        ModelAndView s = new ModelAndView("index");
        if(pageNo>totalPagecount||pageNo<=0){
            s.addObject("contents", pageContentVos);
            return s;
        }
        int start = (pageNo-1)*pageSize;
        int limit = pageSize;
        List<Content> pages = contentMapper.getPage(start, limit);
        pageContentVos = ContentConverter.convetToPageDto(pages);
        s.addObject("contents", pageContentVos);
        int prevous = pageNo-1;
        int next = pageNo+1;
        if(prevous>0) {
            s.addObject("previousLink", "/page/" + prevous);
        }
        if(next<=totalPagecount) {
            s.addObject("nextLink", "/page/" + next);
        }
        return s;
    }
}
