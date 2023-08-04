package com.bj.springboot.api.model.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//@RestController相当于@Controller和@ResponseBody的组合，用于返回结果 而并非跳转页面
@Api(tags = "用户接口")
@RestController
public class MyController {
    @ApiOperation(value = "swagger快速使用",notes = "第一个swagger控制器方法")
    @GetMapping("/hello")
    public String swagger(){
        return "生成接口文档";
    }
}
