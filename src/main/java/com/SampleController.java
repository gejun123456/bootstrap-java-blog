package com;

import com.rest.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bruce.ge on 2016/10/23.
 */
@Controller
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class SampleController {
    @RequestMapping("/")
    String home(){
        return "forward:/page/1";
    }

    public static void main(String[] args) {

        SpringApplication.run(SampleController.class,args);
    }

    @Bean
    CommandLineRunner init(final StorageService storageService){
        return new CommandLineRunner(){
            @Override
            public void run(String... args) throws Exception {
//                no need to delete file. 
                storageService.init();
            }
        };
    }
}
