package com.xx.micrweb.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
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
                .description("前后端分离项目,前端vue" +
                        "后端Spring boot + Dubbo分布式项目")
                .build();
        docket = docket.apiInfo(apiInfo);
        docket = docket.select().apis(RequestHandlerSelectors.basePackage("com.xx.micrweb.controller")).build();
        return docket;
    }
}
