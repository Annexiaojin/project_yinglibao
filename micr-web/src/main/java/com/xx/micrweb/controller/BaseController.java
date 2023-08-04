package com.xx.micrweb.controller;

import com.bj.springboot.api.service.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

public class BaseController {
    @Resource
    protected StringRedisTemplate stringRedisTemplate;
    @DubboReference(interfaceClass = PlatBaseInfoService.class,version = "1.0")
    /*平台的基本信息*/
    protected PlatBaseInfoService platBaseInfoService;
    /*产品服务*/
    @DubboReference(interfaceClass = ProductService.class,version = "1.0")
    protected ProductService productService;
    /*某个产品的详情和投资记录*/
    @DubboReference(interfaceClass = InvestService.class,version = "1.0")
    protected InvestService investService;
    @DubboReference(interfaceClass = UserService.class,version = "1.0")
    protected UserService userService;
    @DubboReference(interfaceClass = RechargeSerivce.class,version = "1.0")
    protected RechargeSerivce rechargeSerivce;
    @DubboReference(interfaceClass = BidService.class,version = "1.0")
    protected BidService bidService;
}
