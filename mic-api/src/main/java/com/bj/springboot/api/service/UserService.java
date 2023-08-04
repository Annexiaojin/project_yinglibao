package com.bj.springboot.api.service;

import com.bj.springboot.api.model.User;
import com.bj.springboot.api.pojo.UserAccountInfo;


public interface UserService {
    User queryByPhone(String phone);

    int userRegister(String phone, String pword);
    User userLogin(String phone,String pwd);
    /*实名认证*/
    int modifyName(String phone,String idCard,String name);
    /*获取用户和资金信息*/
    UserAccountInfo queryUserAllInfo(Integer id);
}
