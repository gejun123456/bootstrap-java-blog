package com.rest.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.rest.annotation.SwaggerIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
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
public class SwaggerConfig {

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(Predicates.not(RequestHandlerSelectors.withClassAnnotation(SwaggerIgnore.class)))
                .paths(PathSelectors.any()).build()
                .pathMapping("/").directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(DeferredResult.class, typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                        typeResolver.resolve(WildcardType.class))).useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.GET,
                        Lists.newArrayList(new ResponseMessageBuilder().code(500).message("500 message").responseModel(new ModelRef("Error"))
                                .build())).securitySchemes(Lists.newArrayList(apiKey())).securityContexts(Lists.newArrayList(Lists.newArrayList(securityContext())))
                .enableUrlTemplating(true).globalOperationParameters(Lists.newArrayList(new ParameterBuilder()
                        .name("someGlobalParameter").description("Desciption of someGlobalParameter").modelRef(new ModelRef("string")).parameterType("query")
                        .required(true).build())).tags(new Tag("my bootstrap blog", "All api relating to blog"));
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
