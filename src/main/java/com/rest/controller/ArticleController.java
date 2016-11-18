package com.rest.controller;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.rest.config.BlogProperty;
import com.rest.converter.ContentConverter;
import com.rest.domain.CommentPO;
import com.rest.domain.Content;
import com.rest.mapper.CommentPODao;
import com.rest.mapper.ContentMapper;
import com.rest.vo.CommentVo;
import com.rest.vo.ContentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by bruce.ge on 2016/11/7.
 */
@Controller
public class ArticleController{

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private BlogProperty blogProperty;

    @RequestMapping("/getArticle/{id}")
    public ModelAndView getArticle(@PathVariable("id") int id) {
        Content byId = contentMapper.findById(id);
        ContentVo vo = ContentConverter.convetToVo(byId);
        ModelAndView article = new ModelAndView("article");
        article.addObject("vo", vo);
        //打开了评论 显示评论的区域
        if (blogProperty.isComment()) {
            article.addObject("comment", true);
            //隐藏id在网页中
            //添加评论的内容 和窗口。
            //query by article id  try display the name and content.

        }
        return article;
    }

}
