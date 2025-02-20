package br.com.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.WebAsyncTask;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Serviço de Movies").description("Microserviço responsável pela pesquisa de Movies.")
                .version("v1").license("").licenseUrl("").build();
    }
    
    @Bean
    public Docket docket() {       
        return new Docket(DocumentationType.SWAGGER_2)                
                .apiInfo(apiInfo())
                .genericModelSubstitutes(WebAsyncTask.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.movie"))
                .paths(PathSelectors.any())
                .build();
    }
}

