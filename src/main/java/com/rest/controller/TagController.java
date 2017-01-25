package com.rest.controller;

import com.rest.service.TagPoService;
import com.rest.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author bruce.ge
 * @Date 2017/1/25
 * @Description
 */
@Controller
public class TagController {

    @Autowired
    private TagPoService tagPoService;

    @GetMapping("/tag")
    public ModelAndView viewTags() {
        List<TagVo> all = tagPoService.findAll();
        ModelAndView tagView = new ModelAndView("tag");
        tagView.addObject("tags", all);
        return tagView;
    }
}
