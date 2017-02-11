package com.rest.config;

import com.rest.intercetors.ExecutionInterceptor;
import com.rest.local.MyLocaleCookieLocaleResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.inject.Inject;
import javax.servlet.Filter;
import java.util.Arrays;
import java.util.List;

/**
 * @Author bruce.ge
 * @Date 2017/1/17
 * @Description
 */
@Configuration
@Slf4j
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
    @Inject
    private Environment env;

    @Autowired
    @Qualifier("authInterceptor")
    private HandlerInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //remove page will forward. //how to know the forward request real time cost?
        registry.addInterceptor(new ExecutionInterceptor());
        registry.addInterceptor(authInterceptor);
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        registry.addInterceptor(localeChangeInterceptor);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(Constants.SPRING_PROFILE_PRODUCTION)) {
            log.info("add resource handle with prod profile");
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/").setCachePeriod(3600 * 24);
        } else {
            log.info("add resourcehandler with dev propfile");
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
    }


    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        MyLocaleCookieLocaleResolver myLocaleCookieLocaleResolver = new MyLocaleCookieLocaleResolver();
        myLocaleCookieLocaleResolver.setCookieName("LOCALE_KEY");
        return myLocaleCookieLocaleResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasenames("MessagesBundle", "ValidationMessages");
        return resourceBundleMessageSource;
    }


    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter  = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registration.setFilter(characterEncodingFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }

}
