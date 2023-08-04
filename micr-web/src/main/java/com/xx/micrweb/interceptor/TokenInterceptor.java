package com.xx.micrweb.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xa.common.enums.ResCode;
import com.xa.common.util.JwtUtil;
import com.xx.micrweb.view.ResResult;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TokenInterceptor implements HandlerInterceptor {
    private String secret = "";

    public TokenInterceptor(String secret) {
        this.secret = secret;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是options 放行
        if ("OPTIONS" .equalsIgnoreCase(request.getMethod())){
            return true;
        }
        Boolean isSuccess = false;
        //获取token 进行验证
        try{
            String requestUid = request.getHeader("uId");
            String requestToken = request.getHeader("Authorization");
            if (StringUtils.isNotBlank(requestToken)){
                String jwt = requestToken.substring(7);
                //读取jwt
                JwtUtil jwtUtil = new JwtUtil(secret);
                Claims claims = jwtUtil.readJwt(jwt);
                //读取jwt中的数据
                Integer jwtUid = claims.get("userId", Integer.class);
                if (requestUid.equals(String.valueOf(jwtUid))){
                    //token和发起请求的用户是同一个
                    isSuccess = true;
                }
            }
        }catch (Exception e){
            isSuccess = false;
            e.printStackTrace();
        }
        //token没有通过验证，需要给前端提示
        if (isSuccess == false){
            ResResult result = ResResult.fail();
            result.setRcode(ResCode.TOKEN_INVALID);
            //使用HttpServletResponse输出json
            String s = JSONObject.toJSONString(result);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print(s);
            out.flush();
            out.close();
        }
        return isSuccess;
    }
}
