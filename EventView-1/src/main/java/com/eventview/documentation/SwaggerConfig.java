package com.eventview.documentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    public static final String PATH = "/docs";

    @Bean
    public Docket swaggerConfiguration(){

        return new Docket((DocumentationType.SWAGGER_2))
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eventview"))
                .build();
    }

    private ApiInfo apiDetails() {
        return new ApiInfoBuilder().title("EventNotifier API")
                .description("REST API to notify events")
                .version( "1.0")
                .contact(new Contact("Kshitesh Routhu","www.github.com/kshithesh","kshithesh.r@gmail.com"))
                .termsOfServiceUrl("Terms and conditions")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PATH + "/**").addResourceLocations("classpath:/META-INF/resources/");
    }
}
