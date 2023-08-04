package com.xx.micrweb.controller;

import com.bj.springboot.api.model.ProductInfo;
import com.bj.springboot.api.pojo.BidInfoProduct;
import com.bj.springboot.api.pojo.MultiProduct;
import com.xa.common.enums.ResCode;
import com.xa.common.util.CommomUtil;
import com.xx.micrweb.view.PageInfo;
import com.xx.micrweb.view.ResResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "理财产品功能")
@RestController
@RequestMapping("/v1")
public class ProductController extends BaseController{
    @ApiOperation(value = "首页三类产品列表",notes = "一个新手宝，一个优选、一个散标")
    @GetMapping("/product/info")
    public ResResult queryProductIndex(){
        ResResult resResult =  ResResult.success();;
        MultiProduct multiProduct = productService.queryIndexPageProducts();
        resResult.setOneObj(multiProduct);
        return resResult;
    }
    @ApiOperation(value = "产品分页查询",notes = "按产品类型分页查询")
    @GetMapping("/product/list")
    public ResResult queryProductByType(@RequestParam("ptype") Integer pType,
                                        @RequestParam(value = "pageNo",required = false,defaultValue = "1") Integer pageNo,
                                        @RequestParam(value="pageSize",required = false,defaultValue = "10") Integer pageSize){
        ResResult resResult = ResResult.fail();
        if (pType != null && (pType == 0 || pType == 1 || pType == 2)){
            pageNo = CommomUtil.defaultPageNo(pageNo);
            pageSize = CommomUtil.defaultPageSize(pageSize);
            /*产品总数*/
            Integer record = productService.queryCountByType(pType);
            if (record > 0) {
                /*产品集合*/
                List<ProductInfo> productInfos = productService.queryByTypeList(pType, pageNo, pageSize);
                resResult = ResResult.success();
                resResult.setList(productInfos);
                PageInfo page = new PageInfo(pageNo, pageSize, record);
                resResult.setPage(page);
            }

        }else{
            resResult.setRcode(ResCode.REQUEST_PARAM_ERR);
        }
        return resResult;
    }
    /*查询某个产品的详情和投资记录*/
    @ApiOperation(value = "产品详情",notes = "查询某个产品的详细信息和投资记录")
    @GetMapping("/product/details")
    public ResResult queryProductDetail(@RequestParam("productId") Integer id){
        ResResult resResult = ResResult.fail();
        if (id != null && id > 0){
            //查询产品的详细信息
            ProductInfo productInfo = productService.selectById(id);
            if (productInfo != null) {
                //查询产品的投资记录
                List<BidInfoProduct> bidInfoProducts = investService.queryBidListById(id, 0, 5);
                resResult = ResResult.success();
                resResult.setOneObj(productInfo);
                resResult.setList(bidInfoProducts);
            }
        }
        return resResult;
        /*
        * https://wx.jdcloud.com/market/datas/5/10665
        * */
    }
}
