package com.xx.micrweb.controller;

import com.xa.common.constanst.RedisKey;
import com.xa.common.enums.ResCode;
import com.xa.common.util.CommomUtil;
import com.xx.micrweb.service.SmsService;
import com.xx.micrweb.view.ResResult;
import io.swagger.annotations.Api;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "短信业务")
@RestController
@RequestMapping("/v1/sms")
public class MessController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource(name = "smsCodeRegisterImpl")
    private SmsService service;
    @Resource(name = "smsCodeLoginImpl")
    private SmsService loginService;
    @GetMapping("/code/register")
    /*发送注册验证码短信*/
    public ResResult sendCodeRegister(String phone){
        ResResult resResult = ResResult.fail();
        if (CommomUtil.checkPhone(phone)){
            //判断验证码是否存在于redis
            String key = RedisKey.KEY_SMS_CODE_REG + phone;
            if (stringRedisTemplate.hasKey(key)){
                resResult = ResResult.success();
                resResult.setRcode(ResCode.SMS_PHONE_EXIST);
            }else{
                boolean isSuccess = service.sendSms(phone);
                if (isSuccess){
                    resResult = ResResult.success();
                }
            }
        }else{
            resResult.setRcode(ResCode.PHONE_TYPE);
        }
       return resResult;
    }

    @GetMapping("/code/login")
    /*发送登录验证码短信*/
    public ResResult sendCodeLogin(String phone){
        ResResult resResult = ResResult.fail();
        if (CommomUtil.checkPhone(phone)){
            //判断验证码是否存在于redis
            String key = RedisKey.KEY_SMS_CODE_LOGIN + phone;
            if (stringRedisTemplate.hasKey(key)){
                resResult = ResResult.success();
                resResult.setRcode(ResCode.SMS_PHONE_EXIST);
            }else{
                boolean isSuccess = loginService.sendSms(phone);
                if (isSuccess){
                    resResult = ResResult.success();
                }
            }
        }else{
            resResult.setRcode(ResCode.PHONE_TYPE);
        }
        return resResult;
    }
}
