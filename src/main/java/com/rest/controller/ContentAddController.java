package com.rest.controller;

import com.rest.Request.AddContentRequest;
import com.rest.annotation.NeedAuth;
import com.rest.converter.ContentConverter;
import com.rest.domain.Content;
import com.rest.domain.ContentTime;
import com.rest.mapper.ContentMapper;
import com.rest.mapper.ContentTimeMapper;
import com.rest.service.SearchService;
import com.rest.utils.AntiSamyUtils;
import com.rest.utils.MarkDownUtil;
import org.apache.commons.lang3.StringUtils;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Calendar;

/**
 * Created by bruce.ge on 2016/11/6.
 */
@Controller
public class ContentAddController {
    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private ContentTimeMapper contentTimeMapper;

    @Autowired
    private SearchService searchService;

    @RequestMapping("/addContent")
    @ResponseBody
    @NeedAuth
    public boolean addContent(AddContentRequest request) throws ScanException, PolicyException {
        //which shall redirect when ok.
        request.setTitle(AntiSamyUtils.getCleanHtml(request.getTitle()));
        Calendar calendar = Calendar.getInstance();
        Content content = ContentConverter.convertToContent(request);
        contentMapper.addContent(content);
        ContentTime time = new ContentTime();
        time.setYear(calendar.get(Calendar.YEAR));
        time.setMonth(calendar.get(Calendar.MONTH) + 1);
        time.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        time.setContent_id(content.getId());
        contentTimeMapper.insert(time);
        //add data to lucene.
        searchService.addSource(request.getTitle(), MarkDownUtil.removeMark(request.getSourceContent()), content.getId());
        return true;
    }

    @NeedAuth(redirectBack = true)
    @RequestMapping("/add")
    public String addPage() {
        return "add";
    }
}
