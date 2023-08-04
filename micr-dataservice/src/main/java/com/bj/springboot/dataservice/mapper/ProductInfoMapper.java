package com.bj.springboot.dataservice.mapper;

import com.bj.springboot.api.model.ProductInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductInfoMapper {
    /*利率平均值*/
    BigDecimal queryAvgRate();
    /*按产品类型分类查询*/
    List<ProductInfo> selectByTypeLimit(@Param("ptype") Integer ptype,
                                        @Param("offset") Integer offset,
                                        @Param("rows") Integer rows);

    Integer queryCountByType(@Param("ptype") Integer ptype);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);
}