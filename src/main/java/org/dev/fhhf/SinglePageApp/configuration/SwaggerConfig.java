package org.dev.fhhf.SinglePageApp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.regex("/spapp.*"))//.ant("/emp/*"))
                .apis(RequestHandlerSelectors.basePackage("org.dev.fhhf.SinglePageApp"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails(){
        return new ApiInfo(
                "Employees API",
                "Single Page App Project",
                "1.0",
                "https://single-page-employees-app.herokuapp.com/",
                new springfox.documentation.service.Contact(
                        "Fabian Hernández",
                        "https://github.com/BalthazRBlake",
                        "fabianhumbertohernandez@gmail.com"),
                "API License",
                "https://github.com/BalthazRBlake",
                Collections.emptyList()
        );
    }
}

