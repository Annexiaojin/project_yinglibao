package com.xx.micrweb.controller;

import com.bj.springboot.api.model.RechargeRecord;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.xa.common.enums.ResCode;
import com.xx.micrweb.view.ResResult;
import com.xx.micrweb.view.ResultView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@Api(tags = "充值业务")
@RequestMapping("/v1")
public class RechargeController extends BaseController {
    @ApiOperation(value = "查询某个用户的充值记录")
    @GetMapping("/recharge")
    public ResResult selectByUId(@RequestHeader("uId") Integer uId,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNo,
                                 @RequestParam(required = false,defaultValue = "5") Integer pageSize){
        ResResult resResult = ResResult.fail();
        if (uId != null && uId > 0){
            List<RechargeRecord> records = rechargeSerivce.selectByUId(uId, pageNo, pageSize);
            resResult.setRcode(ResCode.SUCC);
            resResult.setList(toView(records));
        }
        return resResult;
    }
    private List<ResultView> toView(List<RechargeRecord> records){
        List<ResultView> objects = new ArrayList<>();
        records.forEach( src -> {
            objects.add(new ResultView(src));
        });
        return  objects;
    }
}
