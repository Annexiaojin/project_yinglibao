package com.xx.micrweb.settings;

import com.xx.micrweb.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Value("${jwt.key}")
    private String jwtKey;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        TokenInterceptor tokenInterceptor = new TokenInterceptor(jwtKey);
        String [] addPath = {"/v1/user/realName"};
        registry.addInterceptor(tokenInterceptor).addPathPatterns(addPath);
    }

    //处理跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //addMapping 需要处理的请求地址
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:8080") //可跨域的域名
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
