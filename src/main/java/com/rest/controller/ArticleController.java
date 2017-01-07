package com.rest.controller;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.rest.config.BlogProperty;
import com.rest.converter.ContentConverter;
import com.rest.domain.CommentPO;
import com.rest.domain.Content;
import com.rest.mapper.CommentPODao;
import com.rest.mapper.ContentMapper;
import com.rest.service.MessageSourceService;
import com.rest.vo.CommentVo;
import com.rest.vo.ContentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by bruce.ge on 2016/11/7.
 */
@Controller
public class ArticleController {

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private BlogProperty blogProperty;


    @Autowired
    private MessageSourceService messageSourceService;

    @Autowired
    private CommentPODao commentPODao;

    @GetMapping("/getArticle/{id}")
    public ModelAndView getArticle(@PathVariable("id") int id) {
        Content byId = contentMapper.findById(id);
        ContentVo vo = ContentConverter.convetToVo(byId);
        ModelAndView article = new ModelAndView("article");
        article.addObject("vo", vo);
        //打开了评论 显示评论的区域
        if (blogProperty.isComment()) {
            article.addObject("comment", true);
            List<CommentVo> vos = getCommentVos(id);
            article.addObject("commentVos", vos);
        }
        return article;
    }

    private List<CommentVo> getCommentVos(int articleId) {
        List<CommentPO> select =
                commentPODao.findByArticleId(articleId);
        Date now = new Date();
        return FluentIterable.from(select).transform(new Function<CommentPO, CommentVo>() {
            @Override
            public CommentVo apply(CommentPO commentPO) {
                CommentVo vo = new CommentVo();
                vo.setComment(commentPO.getContent());
                vo.setName(commentPO.getUsername());
                vo.setId(commentPO.getId());
                vo.setParentId(commentPO.getReply_id());
                vo.setAddtime(commentPO.getUpdatetime());
                vo.setViewed(commentPO.getViewed());
                Date updatetime = commentPO.getUpdatetime();
                long pass = now.getTime() - updatetime.getTime();
                long days = pass / 24 / 1000 / 60 / 60;
                if (days > 0) {
                    String daysbefore = messageSourceService.getMessage("daysbefore");
                    vo.setAgo(days + " " + daysbefore);
                } else {
                    long hours = pass / 1000 / 60/60;
                    if (hours > 0) {
                        String hoursago = messageSourceService.getMessage("hoursbefore");
                        vo.setAgo(hours + " " + hoursago);
                    } else {
                        long minutes = pass / 1000 / 60;
                        String minuteAgo = messageSourceService.getMessage("minutesBefore");
                        vo.setAgo(minutes + " " + minuteAgo);
                    }
                }
                return vo;
            }
        }).toSortedList(new Comparator<CommentVo>() {
            @Override
            public int compare(CommentVo o1, CommentVo o2) {
                return o1.getId() - o2.getId();
            }
        });
    }

}
