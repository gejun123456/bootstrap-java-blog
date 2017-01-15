package com.rest.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.rest.annotation.SwaggerIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by bruce.ge on 2016/12/24.
 */
@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig {

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("my java blog apis").
                apiInfo(apiInfo()).
                select()
                .apis(Predicates.or(RequestHandlerSelectors.withClassAnnotation(RestController.class),
                        RequestHandlerSelectors.withMethodAnnotation(ResponseBody.class), RequestHandlerSelectors.withClassAnnotation(ResponseBody.class)))
                .apis(Predicates.not(RequestHandlerSelectors.withClassAnnotation(SwaggerIgnore.class)))
                .paths(Predicates.not(PathSelectors.regex("^/error$"))).build()
                .pathMapping("/").directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(DeferredResult.class, typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                        typeResolver.resolve(WildcardType.class))).useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.GET,
                        Lists.newArrayList(new ResponseMessageBuilder().code(500).message("500 message").responseModel(new ModelRef("Error"))
                                .build())).securitySchemes(Lists.newArrayList(apiKey())).securityContexts(Lists.newArrayList(Lists.newArrayList(securityContext())))
                .tags(new Tag("my bootstrap blog", "All api relating to blog"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Bootstrap java blog rest api")
                .description("providing api for bootstrap blog like register use, add content ect")
                .termsOfServiceUrl("https://brucege.com")
//                .license("Apache License Version 2.0")
//                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("2.0")
                .build();
    }


    private ApiKey apiKey() {
        return new ApiKey("mykey", "api_key", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        return Lists.newArrayList(new SecurityReference("myKey", authorizationScopes));
    }
}
