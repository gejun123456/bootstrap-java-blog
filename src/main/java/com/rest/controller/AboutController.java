package com.rest.controller;

import com.rest.annotation.ExecutionTime;
import com.rest.service.AboutService;
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
        return aboutService.getAbout()
                .map(about -> {
                    if (about.getSourceContent() != null) {//
                        view.addObject("aboutContent", about.getSourceContent());
                        return view;
                    }
                    return null;
                })
                .orElseGet(() -> {
                    view.addObject("aboutContent", "");
                    return view;
                });
    }
}
