package com.bj.springboot.dataservice.mapper;

import com.bj.springboot.api.model.RechargeRecord;

import java.util.List;

public interface RechargeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RechargeRecord record);

    int insertSelective(RechargeRecord record);

    RechargeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RechargeRecord record);

    int updateByPrimaryKey(RechargeRecord record);
    List<RechargeRecord> selectByUId(Integer uId,Integer pageNo,Integer pageSize);
}