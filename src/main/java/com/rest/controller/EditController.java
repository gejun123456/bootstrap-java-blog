package com.rest.controller;

import com.rest.Request.AddContentRequest;
import com.rest.Request.EditContentRequest;
import com.rest.annotation.AuthEnum;
import com.rest.annotation.NeedAuth;
import com.rest.converter.ContentConverter;
import com.rest.domain.Content;
import com.rest.domain.ContentTime;
import com.rest.mapper.ContentMapper;
import com.rest.service.SearchService;
import com.rest.utils.MarkDownUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

/**
 * Created by bruce.ge on 2016/11/13.
 */
@Controller
public class EditController {
    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private SearchService searchService;

    @RequestMapping("/edit/{id}")
    @NeedAuth(auth = AuthEnum.ADMIN)
//todo.    when two people edit the same article, how to inform each other. add lock to database?
    public ModelAndView edit(@PathVariable("id") int id){
        Content byId = contentMapper.findById(id);
        //get the source content.
        ModelAndView edit = new ModelAndView("edit");
        edit.addObject("title",byId.getTitle());
        edit.addObject("source_content",byId.getSource_content());
        edit.addObject("source_id",id);
        return edit;
    }


    @RequestMapping("editContent")
    @NeedAuth(auth = AuthEnum.ADMIN)
    @ResponseBody
    public boolean editContent(EditContentRequest request){
        Calendar calendar = Calendar.getInstance();
        Content content = ContentConverter.convertToContent(request);
        contentMapper.updateContent(content);

        //add data to lucene.
        searchService.update(request.getTitle(), MarkDownUtil.removeMark(request.getSourceContent()), content.getId());
        return true;
    }
}
