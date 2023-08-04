package com.bj.springboot.api.model;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*将该类作为springboot的启动类*/
@EnableSwagger2
@EnableSwaggerBootstrapUI
@SpringBootApplication
public class MySwagger {
    public static void main(String[] args) {
        SpringApplication.run(MySwagger.class,args);
    }
}
