package com;

import com.rest.config.BlogProperty;
import com.rest.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

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
public class SampleController {

    @Autowired
    private BlogProperty blogProperty;

    @RequestMapping("/")
    String home() {
        return "forward:/page/1";
    }

    public static void main(String[] args) {

        SpringApplication.run(SampleController.class, args);
    }

    @Bean
    CommandLineRunner init(final StorageService storageService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                no need to delete file.
                storageService.init();
            }
        };
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("MessagesBundle");
        return resourceBundleMessageSource;
    }

    //    the bean name shall be this.
    @Bean
    public LocaleResolver localeResolver() {
        FixedLocaleResolver fixedLocaleResolver = new FixedLocaleResolver(blogProperty.getLocale());
        return new LocaleResolver() {
            @Override
            public Locale resolveLocale(HttpServletRequest request) {
                return fixedLocaleResolver.resolveLocale(request);
            }

            @Override
            public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
                fixedLocaleResolver.setLocale(request, response, locale);
            }
        };
    }
}
