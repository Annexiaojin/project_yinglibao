package com.xx.micrweb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xa.common.constanst.RedisKey;
import com.xx.micrweb.config.JdwxSmsConfig;
import com.xx.micrweb.service.SmsService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service(value = "smsCodeLoginImpl")
public class SmsCodeLoginImpl implements SmsService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private JdwxSmsConfig smsConfig;
    @Override
    public boolean sendSms(String phone) {
        Boolean send = false;
        //设置验证码的随机4位数
       String random = RandomStringUtils.randomNumeric(4);
        System.out.println("验证码为"+random);
       //更新content中的%s
        String content = String.format(smsConfig.getLoginContent(), random);
        //使用httpClient发送 get 请求给第三方
        CloseableHttpClient client = HttpClients.createDefault();
        String url = smsConfig.getUrl()+"?mobile="+phone +"&content="+content+"&appkey="+smsConfig.getAppKey();
        HttpGet get = new HttpGet(url);
        try {
            CloseableHttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                //得到返回的数据，json
                /*String text = EntityUtils.toString(response.getEntity());*/
                String text="{\n" +
                        " \"code\":\"10000\",\n"+
                        " \"charge\":\"false\",\n"+
                        " \"remain\":1305,\n"+
                        " \"msg\":\"查询成功\",\n"+
                        " \"result\":{\n"+
                        "           \"ReturnStatus\":\"Success\",\n"+
                        "           \"Message\":\"ok\",\n"+
                        "           \"RemainPoint\":420842,\n"+
                        "           \"TaskID\":18424321,\n"+
                        "           \"SuccessCounts\":1\n"+
                        "          }\n"+
                        "}";
                //解析json
                if (StringUtils.isNotBlank(text)){
                    JSONObject jsonObject = JSONObject.parseObject(text);
                    //第三方接口调用成功
                    if ("10000".equals(jsonObject.getString("code"))){
                        //读取result中的key，ReturnStatus
                        if("Success".equalsIgnoreCase(jsonObject.getJSONObject("result").getString("ReturnStatus"))){
                            //短信发送成功
                            send = true;
                            //把短信验证码 存在redis中
                            String key = RedisKey.KEY_SMS_CODE_LOGIN + phone;
                            stringRedisTemplate.boundValueOps(key).set(random,3, TimeUnit.MINUTES);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return send;
    }
    /*检验验证码是否与前端传过来的一致*/
    @Override
    public boolean checkMcode(String phone, String mCode) {
        String key =  RedisKey.KEY_SMS_CODE_LOGIN + phone;
        if (stringRedisTemplate.hasKey(key)){
            //获取redis中的验证码
            String redisCode = stringRedisTemplate.boundValueOps(key).get();
            //如果redis中的验证码和前端传过来的验证码一致
            if (mCode.equals(redisCode)){
                return true;
            }
        }
        return false;
    }
}
