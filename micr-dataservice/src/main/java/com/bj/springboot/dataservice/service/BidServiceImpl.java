package com.bj.springboot.dataservice.service;

import com.bj.springboot.api.model.BidInfo;
import com.bj.springboot.api.pojo.BidInfoView;
import com.bj.springboot.api.service.BidService;
import com.bj.springboot.dataservice.mapper.BidInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@DubboService(interfaceClass = BidService.class,version = "1.0")
public class BidServiceImpl implements BidService {
    @Resource
    private BidInfoMapper bidInfoMapper;
    @Override
    public List<BidInfoView> selectById(Integer uId, Integer pageNo, Integer pageSize) {
        List<BidInfoView> objects = new ArrayList<>();
        if (uId != null && uId > 0){
            objects = bidInfoMapper.selectById(uId,pageNo,pageSize);
        }
        return objects;
    }
}
