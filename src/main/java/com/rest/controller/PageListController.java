package com.rest.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.rest.annotation.ExecutionTime;
import com.rest.converter.ContentConverter;
import com.rest.domain.Content;
import com.rest.enums.StatusEnum;
import com.rest.mapper.ContentMapper;
import com.rest.service.TagPoService;
import com.rest.vo.PageContentVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by bruce.ge on 2016/11/6.
 */
@Controller
public class PageListController {
    @Autowired
    private ContentMapper contentMapper;


    @Autowired
    private TagPoService tagPoService;


    @GetMapping("/getPage")

    @Deprecated
    //穿一个page参数
    public List<PageContentVo> getList(@RequestParam("page") int page, @RequestParam(value = "pagesize", defaultValue = "10") int pageSize) {
        //shiro test code.
        int start = (page - 1) * pageSize;
        int limit = pageSize;
        List<Content> pages = contentMapper.getPageWithStatus(StatusEnum.ACTIVE.getValue(), start, limit);
        List<PageContentVo> pageContentVos = ContentConverter.convetToPageDto(pages);
        for (PageContentVo pageContentVo : pageContentVos) {
            pageContentVo.setTags(Joiner.on(",").join(tagPoService.findTagsForContent(pageContentVo.getId())));
        }
        return pageContentVos;
    }

    @GetMapping("/getpagecount")
    public int getPageCount(@RequestParam(value = "pagesize", defaultValue = "10") int pageSize) {
        int count = contentMapper.getCount();
        return count / pageSize;
    }


    @GetMapping("/page/{pageNo}")
    @ExecutionTime(logToDatabase = true)
    @ApiOperation("获取某一页的数据")
    public ModelAndView getPage(@PathVariable("pageNo") int pageNo) {
        PageHelper.startPage(pageNo, 5);
        List<PageContentVo> pageContentVos = Lists.newArrayList();
        List<Content> activeContent = contentMapper.findByStatusOrderByAddtimeDesc(StatusEnum.ACTIVE.getValue());
        pageContentVos = ContentConverter.convetToPageDto(activeContent);
        for (PageContentVo pageContentVo : pageContentVos) {
            pageContentVo.setTags(Joiner.on(",").join(tagPoService.findTagsForContent(pageContentVo.getId())));
        }
        Page page = (Page) activeContent;
        int pages = page.getPages();
        ModelAndView s = new ModelAndView("index");
        if (pageNo > pages || pageNo <= 0) {
            s.addObject("contents", pageContentVos);
            return s;
        }
        s.addObject("contents", pageContentVos);
        int prevous = pageNo - 1;
        int next = pageNo + 1;
        if (prevous > 0) {
            s.addObject("previousLink", "/page/" + prevous);
        }
        if (next <= pages) {
            s.addObject("nextLink", "/page/" + next);
        }
        return s;
    }
}
