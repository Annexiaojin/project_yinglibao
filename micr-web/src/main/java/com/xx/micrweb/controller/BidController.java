package com.xx.micrweb.controller;

import com.bj.springboot.api.model.BidInfo;
import com.bj.springboot.api.pojo.BidInfoView;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.xa.common.enums.ResCode;
import com.xa.common.util.CommomUtil;
import com.xx.micrweb.view.ResResult;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "投资业务")
@RestController
@RequestMapping("/v1")
public class BidController extends BaseController{
    @GetMapping("/bid")
    public ResResult selectById(@RequestHeader("uId")Integer uId,
                                @RequestParam(required = false,defaultValue = "1") Integer pageNo,
                                @RequestParam(required = false,defaultValue = "5") Integer pageSize){
        ResResult result = ResResult.fail();
        if (uId != null && uId > 0 ){
            pageNo = CommomUtil.defaultPageNo(pageNo);
            pageSize = CommomUtil.defaultPageSize(pageSize);
            List<BidInfoView> bidInfos = bidService.selectById(uId, pageNo, pageSize);
            result.setRcode(ResCode.SUCC);
            result.setList(bidInfos);
        }
        return result;
    }
}
