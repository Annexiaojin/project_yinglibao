package com.xx.micrweb;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.xa.common.util.JwtUtil;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/*启动swagger和ui*/
@EnableSwaggerBootstrapUI
@EnableSwagger2
/*启动dubbo服务*/
@EnableDubbo
@SpringBootApplication
public class MicrWebApplication {
    @Value("${jwt.key}")
    private String key;
    @Bean
    public JwtUtil jwtUtil(){
        JwtUtil jwt = new JwtUtil(key);
        return jwt;
    };


    public static void main(String[] args) {
        SpringApplication.run(MicrWebApplication.class, args);
    }

}
