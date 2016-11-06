package com.rest.controller;

import com.rest.converter.ContentConverter;
import com.rest.domain.Content;
import com.rest.mapper.ContentMapper;
import com.rest.vo.ContentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bruce.ge on 2016/11/7.
 */
@Controller
public class ArticleController {

    @Autowired
    private ContentMapper contentMapper;

    @RequestMapping("/getArticle/{id}")
    public ModelAndView getArticle(@PathVariable("id") int id){
        Content byId = contentMapper.findById(id);
        ContentVo vo = ContentConverter.convetToVo(byId);
        ModelAndView article = new ModelAndView("article");
        article.addObject("vo",vo);
        return article;
    }

}
