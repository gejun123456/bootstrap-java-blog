package com.rest.controller;

import com.rest.Request.AddTagRequest;
import com.rest.Request.DeleteTagRequest;
import com.rest.Request.EditTagRequest;
import com.rest.annotation.AuthEnum;
import com.rest.annotation.NeedAuth;
import com.rest.domain.TagPo;
import com.rest.service.TagPoService;
import com.rest.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @Author bruce.ge
 * @Date 2017/1/25
 * @Description
 */
@Controller
public class TagController {

    @Autowired
    private TagPoService tagPoService;

    @GetMapping("/tag")
    public ModelAndView viewTags() {
        List<TagVo> all = tagPoService.findAll();
        ModelAndView tagView = new ModelAndView("tag");
        tagView.addObject("tags", all);
        return tagView;
    }


    @PostMapping("/getTags")
    @ResponseBody
    public List<TagVo> getAllTags() {
        List<TagVo> all = tagPoService.findAll();
        return all;
    }


    @GetMapping("/tag/add")
    @ResponseBody
    @NeedAuth(AuthEnum.ADMIN)
    public ResponseEntity<?> addTag(@Valid AddTagRequest request) {
        TagPo pojo = TagPo.builder()
            .tagName(request.getName())
            .createTime(new Date())
            .updateTime(new Date())
            .build();
        tagPoService.insert(pojo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tag/delete")
    @ResponseBody
    @NeedAuth(AuthEnum.ADMIN)
    public ResponseEntity<?> deleteTag(@Valid DeleteTagRequest request) {
        tagPoService.delete(request.getTagId());
        return ResponseEntity.ok().build();
    }


    @PostMapping("/tag/edit")
    @ResponseBody
    @NeedAuth(AuthEnum.ADMIN)
    // TODO: 2017/1/25 fix it with request
    public ResponseEntity<?> editTag(@Valid EditTagRequest editTagRequest) {
        int i = tagPoService.updateTagNameById(editTagRequest.getNewTagName(), editTagRequest.getTagId());
        return ResponseEntity.ok().build();
    }
}
