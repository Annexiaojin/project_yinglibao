package com.xx.micrweb.controller;

import com.bj.springboot.api.pojo.BaseInfo;
import com.xx.micrweb.view.ResResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "平台信息功能")
@RestController
@RequestMapping("/v1")
public class PlatBaseInfo extends BaseController{
    @ApiOperation(value = "平台三项基本信息",notes = "注册人数、平均利率、总投资金额")
    @GetMapping("/plat/info")
    public ResResult queryPlatBaseInfo(){
        BaseInfo baseInfo = platBaseInfoService.queryPlatBaseInfo();
        ResResult result = new ResResult();
        result.setCode(200);
        result.setMsg("查询成功");
        result.setOneObj(baseInfo);
        return result;
    }

}
