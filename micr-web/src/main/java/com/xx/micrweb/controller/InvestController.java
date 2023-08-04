package com.xx.micrweb.controller;

import com.github.xiaoymin.swaggerbootstrapui.util.CommonUtils;
import com.xa.common.constanst.RedisKey;
import com.xa.common.util.CommomUtil;
import com.xx.micrweb.view.ResResult;
import com.xx.micrweb.view.invest.RankView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Set;
@Api(tags = "投资理财产品")
@RestController
@RequestMapping("/v1")
public class InvestController extends BaseController{
    /*投资排行榜*/
    @ApiOperation(value = "投资排行榜",notes = "显示投资金额最高的3位用户信息")
    @GetMapping("/inverst/rank")
    public ResResult showInvestRank(){
        ArrayList<RankView> rankViews = new ArrayList<>();
        //从redis获取数据
        Set<ZSetOperations.TypedTuple<String>> sets = stringRedisTemplate.boundZSetOps(RedisKey.KEY_INVEST_RANK).reverseRangeWithScores(0, 2);
        sets.forEach(tuple -> {
            rankViews.add(new RankView(CommomUtil.tuoMinPhone(tuple.getValue()), tuple.getScore()));
        });
        ResResult resResult = ResResult.success();
        resResult.setList(rankViews);
        return resResult;
    }
}
