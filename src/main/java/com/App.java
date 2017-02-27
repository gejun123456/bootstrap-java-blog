package com;

import com.rest.storage.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

/**
 * Created by bruce.ge on 2016/10/23.
 */
@Controller
@EnableAutoConfiguration
@Configuration
//@ComponentScan(basePackages = {"com.rest.config", "com.rest.controller", "com.rest.service",
//        "com.rest.aop", "com.rest.mapper", "com.rest.service", "com.rest.storage",
//        "com.rest.intercetors"
//})
@ComponentScan
@EnableAspectJAutoProxy
@Slf4j
public class App {

    @Inject
    private Environment env;

    @RequestMapping("/")
    String home() {
        return "forward:/page/1";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner init(final StorageService storageService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                no need to delete file.
                log.info("start to init storage service");
                storageService.init();
                log.info("storage service inited");
            }
        };
    }


    //    the bean name shall be this.
}
