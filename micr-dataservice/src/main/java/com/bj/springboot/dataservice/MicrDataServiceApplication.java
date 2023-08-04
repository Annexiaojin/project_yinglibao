package com.bj.springboot.dataservice;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//启动dubbo服务
@EnableDubbo
//Mapper扫描表
@MapperScan(basePackages = "com.bj.springboot.dataservice.mapper")
//项目的启动类标志
@SpringBootApplication
public class MicrDataServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicrDataServiceApplication.class,args);
    }
}
