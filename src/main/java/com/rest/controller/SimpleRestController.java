package com.rest.controller;

import com.rest.dto.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by bruce.ge on 2016/10/23.
 */
@RestController
public class SimpleRestController {
    private static final String template="Hello,%s";

    private final AtomicLong counter = new AtomicLong();

    private static Logger logger = LoggerFactory.getLogger(SimpleRestController.class);


    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name",defaultValue = "world")String name){
        logger.error(name);
        return new Greeting(counter.incrementAndGet(),String.format(template,name));
    }


}
