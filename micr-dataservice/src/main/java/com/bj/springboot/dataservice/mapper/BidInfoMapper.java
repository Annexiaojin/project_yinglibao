package com.bj.springboot.dataservice.mapper;

import com.bj.springboot.api.model.BidInfo;
import com.bj.springboot.api.pojo.BidInfoProduct;
import com.bj.springboot.api.pojo.BidInfoView;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BidInfoMapper {
    BigDecimal querySumBigMoney();
    int deleteByPrimaryKey(Integer id);

    int insert(BidInfo record);

    int insertSelective(BidInfo record);

    BidInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BidInfo record);

    int updateByPrimaryKey(BidInfo record);
    List<BidInfoView> selectById(Integer id, Integer pageNo, Integer pageSize);
    List<BidInfoProduct> queryById(@Param("productId") Integer productId, @Param("offset") int offset, @Param("pageSize") Integer pageSize);
}