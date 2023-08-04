package com.bj.springboot.dataservice.service;

import com.bj.springboot.api.pojo.BaseInfo;
import com.bj.springboot.api.service.PlatBaseInfoService;
import com.bj.springboot.dataservice.mapper.BidInfoMapper;
import com.bj.springboot.dataservice.mapper.ProductInfoMapper;
import com.bj.springboot.dataservice.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.math.BigDecimal;

@DubboService(interfaceClass = PlatBaseInfoService.class,version = "1.0")
public class PlatBaseInfoServiceImpl implements PlatBaseInfoService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ProductInfoMapper productInfoMapper;
    @Resource
    private BidInfoMapper bidInfoMapper;
    /*平台的基本信息*/
    @Override
    public BaseInfo queryPlatBaseInfo() {
        int registerUser = userMapper.queryCountUser();
        BigDecimal avgRate = productInfoMapper.queryAvgRate();
        BigDecimal sumBigMoney = bidInfoMapper.querySumBigMoney();
        BaseInfo baseInfo = new BaseInfo(avgRate,sumBigMoney,registerUser);
        return baseInfo;
    }
}
