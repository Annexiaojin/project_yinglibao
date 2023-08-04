package com.bj.springboot.dataservice.service;

import com.bj.springboot.api.model.ProductInfo;
import com.bj.springboot.api.service.ProductService;
import com.bj.springboot.dataservice.mapper.ProductInfoMapper;
import com.xa.common.constanst.YLBConstant;
import com.xa.common.util.CommomUtil;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.bj.springboot.api.pojo.MultiProduct;
@DubboService(interfaceClass = ProductService.class,version = "1.0")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductInfoMapper productInfoMapper;
    @Override
    public List<ProductInfo> queryByTypeList(Integer pType, Integer pageNo, Integer pageSize) {
        List<ProductInfo> productInfos = new ArrayList<>();
        if ( pType == 0 || pType ==1 || pType ==2){
            pageNo = CommomUtil.defaultPageNo(pageNo);
            pageSize = CommomUtil.defaultPageSize(pageSize);
            /*offset为查询的起始索引*/
            int offset = (pageNo - 1) * pageSize;
            productInfos = productInfoMapper.selectByTypeLimit(pType, offset, pageSize);
        }

        return productInfos;
    }
    /*首页的多个产品数据*/
    @Override
    public MultiProduct queryIndexPageProducts() {
        MultiProduct result = new MultiProduct();
        List<ProductInfo> xinShouList = productInfoMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_XINSHOUBAO,0,1);
        List<ProductInfo> youXuanList = productInfoMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_YOUXUAN,0,3);
        List<ProductInfo> sanBiaoList = productInfoMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_SANBIAO,0,3);
        result.setXinShouBao(xinShouList);
        result.setYouXuan(youXuanList);
        result.setSanBiao(sanBiaoList);
        return result;
    }

    @Override
    public Integer queryCountByType(Integer pType) {
        Integer count = 0;
        if ( pType == 0 || pType ==1 || pType ==2) {
             count = productInfoMapper.queryCountByType(pType);
        }
        return count;
    }

    @Override
    public ProductInfo selectById(Integer id) {
        ProductInfo productInfo = null;
        if (id !=null && id > 0){
            productInfo = productInfoMapper.selectByPrimaryKey(id);
        }
        return productInfo;
    }
}
