package com.bj.springboot.api.service;

import com.bj.springboot.api.model.ProductInfo;
import com.bj.springboot.api.pojo.MultiProduct;

import java.util.List;

public interface ProductService {
     /*按类型分页查询*/
     List<ProductInfo> queryByTypeList(Integer pType, Integer pageNo, Integer pageSize);
     /*首页的多个产品数据*/
     MultiProduct queryIndexPageProducts();
     /*查询某类产品的总记录数*/
     Integer queryCountByType(Integer pType);
     //根据产品id查找详细数据
     ProductInfo selectById(Integer id);

}
