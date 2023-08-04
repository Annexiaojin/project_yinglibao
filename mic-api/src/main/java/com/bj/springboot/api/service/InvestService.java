package com.bj.springboot.api.service;

import com.bj.springboot.api.pojo.BidInfoProduct;

import java.util.List;

public interface InvestService {
    //查询某个产品的投资记录
    List<BidInfoProduct> queryBidListById(Integer productId,Integer pageNo,Integer pageSize);
}
