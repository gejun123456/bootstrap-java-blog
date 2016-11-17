package com.rest.controller;

import com.rest.Request.CommentRequest;
import com.rest.converter.CommentConvert;
import com.rest.domain.CommentPO;
import com.rest.mapper.CommentPODao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bruce.ge on 2016/11/18.
 */
@Controller
public class CommentController {
    @Autowired
    private CommentPODao commentPODao;
    private static Logger logger = LoggerFactory.getLogger(CommentController.class);

    @RequestMapping("/comment/{id}")
    public String comment(HttpServletRequest request, @PathVariable(value = "id",required = true)int id,
                          CommentRequest commentRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            logger.info("request error, the request is:{}",commentRequest.toString());
            redirectAttributes.addAttribute("message","request is not right");
            return "redirect:/getArticle/"+id;
        }
        //
        CommentPO po = CommentConvert.createPo(commentRequest,id);
        commentPODao.insert(po);
        return "redirect:/getArticle/"+id;
    }
}
