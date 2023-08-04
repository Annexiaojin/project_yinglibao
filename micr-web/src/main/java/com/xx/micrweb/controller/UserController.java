package com.xx.micrweb.controller;

import com.bj.springboot.api.model.User;
import com.bj.springboot.api.pojo.UserAccountInfo;
import com.xa.common.enums.ResCode;
import com.xa.common.util.CommomUtil;
import com.xa.common.util.JwtUtil;
import com.xx.micrweb.service.SmsService;
import com.xx.micrweb.view.ResResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户功能")
@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController{
    /*注入发送短信的service*/
    @Resource(name="smsCodeRegisterImpl")
    private SmsService smsService;
    @Resource
    private JwtUtil jwtUtil;
    @ApiOperation(value = "手机号注册用户")
    @PostMapping("/register")
    public ResResult userRegister(@RequestParam String phone,
                                  @RequestParam String pword,
                                  @RequestParam String mcode){
        ResResult resResult = ResResult.fail();
        /*手机号格式是否正确*/
        if (CommomUtil.checkPhone(phone)){
            /*密码格式是否正确*/
            if (pword != null && pword.length() ==32){
                if (smsService.checkMcode(phone,mcode)){
                    //可以注册
                  int registerUser = userService.userRegister(phone,pword);
                  if (registerUser == 1){
                      resResult.setRcode(ResCode.SUCC);
                  }else if (registerUser == 2){
                      resResult.setRcode(ResCode.PHONE_EXIST);
                  }else{
                      resResult.setRcode(ResCode.REQUEST_PARAM_ERR);
                  }
                }else{
                    resResult.setRcode(ResCode.SMS_CODE_ERR);
                }
            }else{
                resResult.setRcode(ResCode.REQUEST_PARAM_ERR);
            }
        }else{
            resResult.setRcode(ResCode.PHONE_TYPE);
        }
        return resResult;
    }
    @ApiOperation(value = "判断手机号是否注册过",notes = "在注册功能中判断手机号是否注册")
    @ApiImplicitParam(name = "phone",value = "手机号")
    @GetMapping("/phone/exists")
    public ResResult checkPhone(@RequestParam("phone") String phone){
        ResResult resResult = new ResResult();
        resResult.setRcode(ResCode.PHONE_EXIST);
        if (CommomUtil.checkPhone(phone)){
            //手机号格式正确
            User user = userService.queryByPhone(phone);
            /*说明该手机号未注册过*/
            if (user == null){
                resResult = ResResult.success();
            }
        }else{
            resResult.setRcode(ResCode.PHONE_TYPE);
        }
        return resResult;
    }
    @ApiOperation(value = "用户登录功能")
    @PostMapping("/login")
    public ResResult userLogin(@RequestParam String phone,
                               @RequestParam String pwd) throws Exception {
        ResResult resResult = ResResult.fail();
        if (CommomUtil.checkPhone(phone) &&(pwd != null && pwd.length() == 32)){
               User user = userService.userLogin(phone,pwd);
               if (user != null){
                   //登陆成功 生成token
                   resResult.setRcode(ResCode.SUCC);
                   HashMap<String, Object> data = new HashMap<>();
                   data.put("userId",user.getId());
                   String token = jwtUtil.createJwt(data, 5);
                   resResult =ResResult.success();
                   resResult.setAccessToken(token);
                   HashMap<Object, Object> userInfo = new HashMap<>();
                   userInfo.put("uId",user.getId());
                   userInfo.put("uName",user.getName());
                   userInfo.put("uPhone",user.getPhone());
                   resResult.setOneObj(userInfo);
               }else{
                   resResult.setRcode(ResCode.REQUEST_PARAM_ERR);
               }
        }else{
            resResult.setRcode(ResCode.REQUEST_PARAM_ERR);
        }
        return resResult;

    }
    @ApiOperation(value = "用户实名认证")
    @PostMapping("/realName")
    public ResResult realName(@RequestParam String phoneN,
                              @RequestParam String idCard,
                              @RequestParam String name){
        ResResult resResult = ResResult.fail();
        int i = 0;
        if (CommomUtil.checkPhone(phoneN)){
            if (!StringUtils.isAllEmpty(idCard,name)){
                User user = userService.queryByPhone(phoneN);
                System.out.println(user.getName());
                if (user != null && user.getName().length() !=0 ){
                    resResult.setRcode(ResCode.REALNAME_REPEAT);
                }else{
                    i = userService.modifyName(phoneN,idCard,name);
                    if (i > 0){
                        resResult.setRcode(ResCode.SUCC);
                    }
                }
            }else{
                resResult.setRcode(ResCode.REQUEST_PARAM_ERR);
            }
        }else{
            resResult.setRcode(ResCode.PHONE_TYPE);
        }
        return resResult;
    }
    @ApiOperation(value = "用户中心")
    @GetMapping("/usercenter")
    public ResResult userCenter(@RequestHeader("uId") Integer id){
        ResResult resResult = ResResult.fail();
        if (id != null && id > 0){
            UserAccountInfo userAccountInfo = userService.queryUserAllInfo(id);
            if (userAccountInfo != null){
                resResult.setRcode(ResCode.SUCC);
                Map<String, Object> data = new HashMap<>();
                data.put("name",userAccountInfo.getName());
                data.put("phone",userAccountInfo.getPhone());
                if (userAccountInfo.getLastLoginTime() != null){
                    data.put("loginTime", DateFormatUtils.format(userAccountInfo.getLastLoginTime(),"yyyy-MM-dd HH:mm:ss"));
                }else{
                    data.put("loginTime",DateFormatUtils.format(userAccountInfo.getLastLoginTime(),"-"));
                }
                data.put("money",userAccountInfo.getAvailableMoney());
                 resResult.setOneObj(data);
            }
        }
        return resResult;
    }
}
