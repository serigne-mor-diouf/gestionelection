//package com.dioufserignemor.gmail.gestionelection.swaggerConfig;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import java.util.Collections;
//import static springfox.documentation.builders.PathSelectors.regex;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket postsApi() {
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("DioufSolutionTech")
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.dioufserignemor.gmail"))
//                .paths(regex("/voter.*"))
//                .build()
//                .securitySchemes(Collections.singletonList(apiKey()))
//                .securityContexts(Collections.singletonList(securityContext()));
//
//    }
//
//    private ApiInfo apiInfo() {
//
//        return new ApiInfoBuilder()
//                .title("Platform de gestion de vote de different elections")
//                .description("Microservice Vote est un API qui s'occupe de la Gestion des elections au senegal ," +
//                        "permettant aux electeurs de voter en ligne sans se deplacer.Et de suivre l'evolution des resultats issus des urnes par rapport a votre candidat. ")
//                .termsOfServiceUrl("a.dioufserignemor@gmail.com")
//                .license("DioufSolutionTech")
//                .licenseUrl("https://a.dioufserignemor@gmail.com")
//                .version("1.0")
//                .contact(new Contact("DioufSolutionTech", "https://a.dioufserignemor@gmail.com", "a.dioufserignemor@gmail.com"))
//                .build();
//
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("JWT", "Authorization", "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(Collections.singletonList(new SecurityReference("JWT", new AuthorizationScope[0])))
//                .build();
//    }
//}
