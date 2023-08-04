package com.xx;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class JwtTest {
    @Test
    public void testCreateJwt(){
       /* String s = UUID.randomUUID().toString();
        System.out.println(s);
        System.out.println(s.replaceAll("-",""));*/
         String key = "1f3e3fb413414981995e3f62129e1e5b";
        //创建SecretKey
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        //创建jwt
        Date currDate = new Date();
        HashMap<String, Object> data = new HashMap<>();
        data.put("userId",1001);
        data.put("userName","潇潇");
        String jwt = Jwts.builder().signWith(secretKey, SignatureAlgorithm.HS256)
                .setExpiration(DateUtils.addMinutes(currDate, 10))
                .setIssuedAt(currDate)
                .setId(UUID.randomUUID().toString())
                .setClaims(data).compact();
        System.out.println("jwt = "+jwt);
    }

    @Test
    public void testReadJwt(){
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6Iua9h-a9hyIsInVzZXJJZCI6MTAwMX0.lAZAo-6qdM7p4Ve3r8We7T66tWnU7A_r2JIccQ5xfAg";
        String key = "1f3e3fb413414981995e3f62129e1e5b";
        //创建SecretKey
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        //解析jwt,没有异常  解析成功
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
        //读数据
        Claims body = claims.getBody();
        Integer userId = body.get("userId", Integer.class);
        System.out.println("userId = "+userId);
        String userName = body.get("userName", String.class);
        System.out.println("userName = "+userName);

    }
}
