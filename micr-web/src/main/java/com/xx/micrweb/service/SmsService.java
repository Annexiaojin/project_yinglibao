package com.xx.micrweb.service;

public interface SmsService {
    //发送短信
    boolean sendSms(String phone);
    //检验验证码
    boolean checkMcode(String phone,String mCode);
}
