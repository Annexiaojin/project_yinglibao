package com.bj.springboot.api.service;

import com.bj.springboot.api.model.BidInfo;
import com.bj.springboot.api.pojo.BidInfoProduct;
import com.bj.springboot.api.pojo.BidInfoView;

import java.util.List;

public interface BidService {
    List<BidInfoView> selectById(Integer uId, Integer pageNo, Integer pageSize);
}
