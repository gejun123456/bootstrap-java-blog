package com.rest.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by bruce.ge on 2017/1/8.
 */
@Controller
public class ExecutionTimeController {


    @GetMapping("/viewExecutionTime")
    public String viewExecutionTime() {
        return "";
    }
}
