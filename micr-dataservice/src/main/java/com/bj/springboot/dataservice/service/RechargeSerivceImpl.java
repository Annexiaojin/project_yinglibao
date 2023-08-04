package com.bj.springboot.dataservice.service;

import com.bj.springboot.api.model.RechargeRecord;
import com.bj.springboot.api.service.RechargeSerivce;
import com.bj.springboot.dataservice.mapper.RechargeRecordMapper;
import com.xa.common.util.CommomUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@DubboService(interfaceClass = RechargeSerivce.class,version = "1.0")
public class RechargeSerivceImpl implements RechargeSerivce {
    @Autowired
    private RechargeRecordMapper recordMapper;
    @Override
    public List<RechargeRecord> selectByUId(Integer uId, Integer pageNo, Integer pageSize) {
        List<RechargeRecord> records = new ArrayList<>();
        if (uId != null && uId > 0){
            pageNo = CommomUtil.defaultPageNo(pageNo);
            pageSize = CommomUtil.defaultPageSize(pageSize);
            int offset = (pageNo - 1) * pageSize;
            records = recordMapper.selectByUId(uId,offset,pageSize);
        }
        return records;
    }
}
