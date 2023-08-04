package com.bj.springboot.api.service;

import com.bj.springboot.api.model.RechargeRecord;

import java.util.List;

public interface RechargeSerivce {
    List<RechargeRecord> selectByUId(Integer uId,Integer pageNo,Integer pageSize);
}
