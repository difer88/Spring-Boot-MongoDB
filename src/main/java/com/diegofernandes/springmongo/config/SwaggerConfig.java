package com.diegofernandes.springmongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final ResponseMessage m201 = customMessage201();
    private final ResponseMessage m204put = simpleMessage(204, "User updated successfully");
    private final ResponseMessage m204del = simpleMessage(204, "User deleted successfully");
    private final ResponseMessage m403 = simpleMessage(403, "Not authorized");
    private final ResponseMessage m404 = simpleMessage(404, "Not found");
    private final ResponseMessage m422 = simpleMessage(422, "Validation error");
    private final ResponseMessage m500 = simpleMessage(500, "Unexpected error");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(m403, m404, m500))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m422, m500))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m403, m404, m422, m500))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m403, m404, m500))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.diegofernandes.springmongo.resources"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Project with Spring Boot and MongoDB",
                "API to control user posts using SpringBoot and MongoDB",
                "Version 1.0.1-SNAPSHOT",
                "https://help.github.com/en/articles/github-terms-of-service",
                new Contact(
                        "Diego Fernandes",
                        "https://www.linkedin.com/in/diegofernandes88/",
                        "diegofernandes88@hotmail.com"),
                "GNU General Public License v3.0",
                "https://github.com/difer88/Spring-Boot-MongoDB/blob/master/LICENSE",
                Collections.emptyList() // Vendor Extensions
        );
    }

    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder()
                .code(code)
                .message(msg)
                .build();
    }

    private ResponseMessage customMessage201() {
        Map<String, Header> map = new HashMap<>();
        map.put("Location", new Header("location", "URI of created user", new ModelRef("string")));
        return new ResponseMessageBuilder()
                .code(201)
                .message("User created successfully")
                .headersWithDescription(map)
                .build();
    }

}