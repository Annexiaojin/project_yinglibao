package com.bj.springboot.api.service;

import com.bj.springboot.api.pojo.BaseInfo;

public interface PlatBaseInfoService {
    /*计算利率  注册人数 累计成交金额*/
    BaseInfo queryPlatBaseInfo();
}
