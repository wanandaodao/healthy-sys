package com.wanandaodao.bigevent;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class BigEventApplicationTests {

    @Test
    void contextLoads() {

        Map<String,Object> claims = new HashMap<>();
        claims.put("username","叨叨");
        claims.put("id",1);
        String jwt = JWT.create()
                .withClaim("user",claims)//添加用户数据
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))//设置过期时间
                .sign(Algorithm.HMAC256("wanandaodao"));//添加签名算法
        System.out.println(jwt);
    }

    @Test
    public void test(){
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuWPqOWPqCJ9LCJleHAiOjE3MTgwNTM0MDh9." +
                "0CI-wMrg90a8IpCV8SlXE7wMGfhkRF-_d1FM7WgRqvQ";

        Map<String, Claim> claims = JWT.require(Algorithm.HMAC256("wanandaodao"))
                .build()
                .verify(jwt)
                .getClaims();
        System.out.println(claims.get("user"));

    }
}
