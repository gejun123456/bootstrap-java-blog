package com.rest.controller;

import com.rest.Request.EditContentRequest;
import com.rest.annotation.AuthEnum;
import com.rest.annotation.NeedAuth;
import com.rest.converter.ContentConverter;
import com.rest.domain.Content;
import com.rest.mapper.ContentMapper;
import com.rest.mapper.ContentTimeMapper;
import com.rest.service.SearchService;
import com.rest.utils.AntiSamyUtils;
import com.rest.utils.MarkDownUtil;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bruce.ge on 2016/11/13.
 */
@Controller
public class EditController {
    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private ContentTimeMapper contentTimeMapper;

    @Autowired
    private SearchService searchService;

    @GetMapping("/edit/{id}")
    @NeedAuth(AuthEnum.ADMIN)
//todo.    when two people edit the same article, how to inform each other. add lock to database?
    public ModelAndView edit(@PathVariable("id") int id) {
        Content byId = contentMapper.findById(id);
        //get the source content.
        ModelAndView edit = new ModelAndView("edit");
        edit.addObject("title", byId.getTitle());
        edit.addObject("source_content", byId.getSource_content());
        edit.addObject("source_id", id);
        return edit;
    }


    @PostMapping("editContent")
    @NeedAuth(AuthEnum.ADMIN)
    @ResponseBody
    public boolean editContent(EditContentRequest request) throws ScanException, PolicyException {
        request.setTitle(AntiSamyUtils.getCleanHtml(request.getTitle()));
        Content content = ContentConverter.convertToContent(request);
        contentMapper.updateContent(content);
        //add data to lucene.
        //shall using new thread to do the thing.
        new Thread(() -> searchService.update(request.getTitle(), MarkDownUtil.removeMark(request.getSourceContent()), content.getId())).start();
        return true;
    }

    @GetMapping("/delete/{id}")
    @NeedAuth(AuthEnum.ADMIN)
    public String delete(@PathVariable("id") int id) {
        Content content = new Content();
        content.setId(id);
        contentMapper.deletebyId(content);
        contentTimeMapper.deleteByContentId(id);
        searchService.delete(id);
        return "redirect:/";
    }

}
