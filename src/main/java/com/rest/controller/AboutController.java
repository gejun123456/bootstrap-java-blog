package com.rest.controller;

import com.rest.Request.EditAboutRequest;
import com.rest.annotation.AuthEnum;
import com.rest.annotation.ExecutionTime;
import com.rest.annotation.NeedAuth;
import com.rest.domain.AboutPo;
import com.rest.service.AboutService;
import com.rest.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

/**
 * Created by bruce.ge on 2016/11/7.
 */
@Controller
public class AboutController {

    @Autowired
    private AboutService aboutService;

    @GetMapping("/about")
    @ExecutionTime(logToDatabase = true)
    public ModelAndView getAbout() {
        ModelAndView view = new ModelAndView("about");
        return aboutService.getAbout()
            .map(about -> {
                if (about.getSourceContent() != null) {//
                    view.addObject("aboutContent", about.getMarkdownHtml());
                    return view;
                }
                return null;
            })
            .orElseGet(() -> {
                view.addObject("aboutContent", "");
                return view;
            });
    }


    @GetMapping("/loadUserAbout")
    @ResponseBody
    public String getUserAbout() {
        Optional<AboutPo> about = aboutService.getAbout();
        return about.map((aboutPo -> aboutPo.getSourceContent()))
            .orElse("");
    }


    @PostMapping("/editAbout")
    @ResponseBody
    @NeedAuth(AuthEnum.ADMIN)
    public ResponseEntity<?> editAbout(@Valid @RequestBody EditAboutRequest request) {
        AboutPo aboutPo = convertToAboutPo(request);
        aboutService.insert(aboutPo);
        return ResponseEntity.ok().build();
    }

    private AboutPo convertToAboutPo(EditAboutRequest request) {
        AboutPo aboutPo = new AboutPo();
        aboutPo.setSourceContent(request.getSourceContent());
        aboutPo.setMarkdownHtml(request.getSourceHtml());
        aboutPo.setCreateTime(new Date());
        aboutPo.setUpdateTime(new Date());
        aboutPo.setUserId(SessionUtils.getCurrentUser().getUserId());
        return aboutPo;
    }
}
