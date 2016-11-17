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
public class ArticleController implements MessageSourceAware{

    private MessageSource messageSource;

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private CommentPODao commentPODao;

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
            CommentPO query = new CommentPO();
            query.setArticle_id(id);
            List<CommentPO> select =
                    commentPODao.select(query);
            Date now = new Date();
            List<CommentVo> vos = FluentIterable.from(select).transform(new Function<CommentPO, CommentVo>() {
                @Override
                public CommentVo apply(CommentPO commentPO) {
                    CommentVo vo = new CommentVo();
                    vo.setComment(commentPO.getContent());
                    vo.setName(commentPO.getUsername());
                    //
                    Date updatetime = commentPO.getUpdatetime();
                    long pass = now.getTime()-updatetime.getTime();
                    long days = pass / 24 / 1000/60 / 60;
                    if(days>0){
                        String daysbefore = messageSource.getMessage("daysbefore", new Object[]{}, blogProperty.getLocale());
                        vo.setAgo(days+daysbefore);
                    } else{
                        long hours = pass/1000/60*60;
                        if(hours>0){
                            String hoursago = messageSource.getMessage("hoursbefore",new Object[]{},blogProperty.getLocale());
                            vo.setAgo(hours+hoursago);
                        } else {
                            long minutes = pass/1000/60;
                            String minuteAgo = messageSource.getMessage("minutesBefore",new Object[]{},blogProperty.getLocale());
                            vo.setAgo(minutes+minuteAgo);
                        }
                    }
                    return vo;
                }
            }).toList();
            article.addObject("commentList", vos);
        }
        return article;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
