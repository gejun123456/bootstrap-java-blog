package com.rest.controller;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.rest.Request.CommentRequest;
import com.rest.Request.ReplyCommentRequest;
import com.rest.converter.CommentConvert;
import com.rest.domain.CommentPO;
import com.rest.mapper.CommentPODao;
import com.rest.response.BaseResponse;
import com.rest.response.CodeConstants;
import com.rest.service.MessageSourceService;
import com.rest.utils.HttpHeaderUtil;
import com.rest.vo.CommentVo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by bruce.ge on 2016/11/18.
 */
@RestController
@Api("评论服务")
public class CommentController {
    @Autowired
    private CommentPODao commentPODao;

    @Autowired
    private MessageSourceService messageSourceService;
    private static Logger logger = LoggerFactory.getLogger(CommentController.class);

    @PostMapping("/comment/{id}")
    public BaseResponse comment(HttpServletRequest request, @PathVariable(value = "id", required = true) int id,
                                @Valid CommentRequest commentRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            logger.info("request error, the request is:{}", commentRequest.toString());
            String msg = "";
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                msg += fieldError.getDefaultMessage() + " for " + fieldError.getField() + "</br>";
            }
            return BaseResponse.builder()
                    .code(CodeConstants.validate_fail)
                    .msg(msg)
                    .build();
        }
        //
        CommentPO po = CommentConvert.createPo(commentRequest, id, HttpHeaderUtil.getRemoteAddr(request));
        commentPODao.insert(po);
        return BaseResponse.success(null);
    }


    @Deprecated
    @GetMapping("/getComment/{articleId}")
    @ResponseBody
    public List<CommentVo> getComments(@PathVariable("articleId") int articleId) {
        java.util.List<CommentPO> select =
                commentPODao.findByArticleId(articleId);
        Date now = new Date();
        java.util.List<CommentVo> vos = FluentIterable.from(select).transform(new Function<CommentPO, CommentVo>() {
            @Override
            public CommentVo apply(CommentPO commentPO) {
                CommentVo vo = new CommentVo();
                vo.setComment(commentPO.getContent());
                vo.setName(commentPO.getUsername());
                vo.setId(commentPO.getId());
                vo.setParentId(commentPO.getReply_id());
                vo.setAddtime(commentPO.getUpdatetime());
                Date updatetime = commentPO.getUpdatetime();
                long pass = now.getTime() - updatetime.getTime();
                long days = pass / 24 / 1000 / 60 / 60;
                if (days > 0) {
                    String daysbefore = messageSourceService.getMessage("daysbefore");
                    vo.setAgo(days + " " + daysbefore);
                } else {
                    long hours = pass / 1000 / 60 * 60;
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
        return vos;
    }


    @GetMapping("/reply")
    public String reply(@Valid ReplyCommentRequest request, BindingResult bindingResult, HttpServletRequest servletRequest) {
        if (bindingResult.hasErrors()) {
            logger.info("binding result error the request is {}", request.toString());
            // FIXME: 2017/1/14
            return "failed";
        }
        Integer replyCommentId = request.getReplyCommentId();
        CommentPO select = commentPODao.findById(replyCommentId);
        if (select == null) {
            logger.info("can't find reply comment");
            return "redirect:/getArticle/" + request.getArticleId();
        }
        String username = select.getUsername();

        CommentPO po = new CommentPO();
        po.setAddtime(new Date());
        po.setReply_id(replyCommentId);
        po.setArticle_id(request.getArticleId());
        po.setUpdatetime(new Date());
        po.setUsername(request.getName());
        po.setComment_ip(HttpHeaderUtil.getRemoteAddr(servletRequest));
        po.setContent("reply to  " + username + " : " + request.getContent());
        commentPODao.insert(po);
        return "redirect:/getArticle/" + request.getArticleId();
    }

}
