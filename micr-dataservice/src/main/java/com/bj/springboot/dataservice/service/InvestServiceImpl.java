package com.bj.springboot.dataservice.service;

import com.bj.springboot.api.pojo.BidInfoProduct;
import com.bj.springboot.api.service.InvestService;
import com.bj.springboot.api.service.PlatBaseInfoService;
import com.bj.springboot.dataservice.mapper.BidInfoMapper;
import com.xa.common.util.CommomUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@DubboService(interfaceClass = InvestService.class,version = "1.0")
public class InvestServiceImpl implements InvestService {
    @Resource
    private BidInfoMapper bidInfoMapper;
    @Override
    public List<BidInfoProduct> queryBidListById(Integer productId, Integer pageNo, Integer pageSize) {
        List<BidInfoProduct> bidList = new ArrayList<>();
        if (productId != null && productId > 0){
            pageNo = CommomUtil.defaultPageNo(pageNo);
            pageSize = CommomUtil.defaultPageSize(pageSize);
            int offset = (pageNo - 1) * pageSize;
            bidList = bidInfoMapper.queryById(productId,offset,pageSize);
        }
        return bidList;
    }
}
