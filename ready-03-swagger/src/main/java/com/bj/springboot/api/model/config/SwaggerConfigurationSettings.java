package com.bj.springboot.api.model.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurationSettings {
//    创建DOCKET对象
    @Bean
    public Docket docket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("金融项目")
                .version("1.0")
                .description("前后端分离项目")
                .build();
        docket = docket.apiInfo(apiInfo);
        return docket;
    }
}
