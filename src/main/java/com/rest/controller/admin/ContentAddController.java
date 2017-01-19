package com.rest.controller.admin;

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
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;

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

    @PostMapping("/addContent")
    @ResponseBody
    @NeedAuth
    public ResponseEntity<?> addContent(@Valid @RequestBody AddContentRequest request) throws ScanException, PolicyException {
        //which shall redirect when ok.
        Calendar calendar = Calendar.getInstance();
        User currentUser =
                SessionUtils.getCurrentUser();
        if (currentUser == null) {
            // TODO: 2017/1/20 fix out weather this will happen if will then fix it in exceptionTranslator
            throw new UserSessionTimeOutException();
        }

        Content content = ContentConverter.convertToContent(request, currentUser);
        ContentTime time = new ContentTime();
        time.setYear(calendar.get(Calendar.YEAR));
        time.setMonth(calendar.get(Calendar.MONTH) + 1);
        time.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        try {
            contentService.saveContent(content, time);
        } catch (Exception e) {
            log.error("save content fail, the content is {}, the time is {}", content.toString(), time.toString(), e);
            throw new TransactionException("add content fail");
        }
        //add data to lucene.
        new Thread(() -> searchService.addSource(request.getTitle(), MarkDownUtil.removeMark(request.getSourceContent()), content.getId())).start();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @NeedAuth(redirectBack = true)
    @GetMapping("/add")
    public String addPage() {
        return "add";
    }

    @ExceptionHandler(TransactionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String processTransaction(TransactionException e) {
        return e.getMessage();
    }
}
