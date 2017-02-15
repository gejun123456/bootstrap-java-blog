package com.rest.controller.admin;

import com.google.common.collect.Lists;

import com.rest.Request.AddContentRequest;
import com.rest.annotation.NeedAuth;
import com.rest.bean.User;
import com.rest.controller.customException.TransactionException;
import com.rest.controller.customException.UserSessionTimeOutException;
import com.rest.converter.ContentConverter;
import com.rest.domain.Content;
import com.rest.domain.ContentTime;
import com.rest.service.ContentService;
import com.rest.service.SearchService;
import com.rest.utils.MarkDownUtil;
import com.rest.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

/**
 * Created by bruce.ge on 2016/11/6.
 */
@Controller
@Slf4j
public class ContentAddController {

    @Autowired
    private ContentService contentService;


    @Autowired
    private SearchService searchService;

    @PostMapping(value = "/addContent")
    @ResponseBody
    @NeedAuth
    public ResponseEntity<Void> addContent(@Valid @RequestBody AddContentRequest request) {
        //which shall redirect when ok.
        Calendar calendar = Calendar.getInstance();
        User currentUser =
            SessionUtils.getCurrentUser();
        if (currentUser == null) {
            // TODO: 2017/1/20 fix out weather this will happen if will then fix it in exceptionTranslator
            log.error("current user session time out the request is {}", request.toString());
            throw new UserSessionTimeOutException();
        }
        Content content = ContentConverter.convertToContent(request, currentUser);
        ContentTime time = new ContentTime();
        time.setYear(calendar.get(Calendar.YEAR));
        time.setMonth(calendar.get(Calendar.MONTH) + 1);
        time.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        List<Integer> tags = extractTagFromValue(request.getTagValue());
        try {
            contentService.saveContent(content, time,tags);
        } catch (Exception e) {
            log.error("save content fail,the request is {}, the content is {}, the time is {}", request.toString(), content.toString(), time.toString(), e);
            throw new TransactionException("add content fail", e);
        }
        //add data to lucene.
        new Thread(() -> searchService.addSource(request.getTitle(), MarkDownUtil.removeMark(request.getSourceContent()), content.getId())).start();
        return ResponseEntity.ok().build();
    }

    private List<Integer> extractTagFromValue(String tagValue) {
        List<Integer> tagList = Lists.newArrayList();
        if(StringUtils.isNotBlank(tagValue)){
            String[] split = tagValue.split(",");
            for (String s : split) {
                tagList.add(Integer.parseInt(s));
            }
        }
        return tagList;
    }

    @NeedAuth(redirectBack = true)
    @GetMapping("/add")
    public String addPage() {
        return "add";
    }

    @ExceptionHandler(TransactionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String processTransaction(TransactionException e) {
        return e.getMessage();
    }
}
