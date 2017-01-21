package com.rest.controller;

import com.rest.annotation.ExecutionTime;
import com.rest.service.AboutService;
import com.rest.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
        if (SessionUtils.getCurrentUser().isAdmin()) {
            view.addObject("edit", true);
        }
        return aboutService.getAbout()
                .map(about -> {
                    String content = "";
                    if (about.getSourceContent() != null) {//
                        content = about.getSourceContent();
                        view.addObject("aboutContent", content);
                        return view;
                    }
                    return null;
                })
                .orElseGet(() -> view);
    }
}
