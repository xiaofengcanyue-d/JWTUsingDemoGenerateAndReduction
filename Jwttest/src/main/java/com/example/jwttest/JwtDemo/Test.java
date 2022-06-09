package com.example.jwttest.JwtDemo;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;


public class Test {
    private long time = 1000*60*60*24;
    private String signature = "haojiahuo";

    /**
     *  生成JWT
     */
    @org.junit.Test
    public void jwt(){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header 头
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload  载荷
                .claim("username","zhangsan")
                .claim("role","admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature  签名  加密
                .signWith(SignatureAlgorithm.HS512,signature)
                // 字符串拼接
                .compact();
        System.out.println(jwtToken);
    }

    /**
     * 解码JWT 中数据
     */
    @org.junit.Test
    public void parse(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6InpoYW5nc2FuIiwicm9sZSI6ImFkbWluIiwic3ViIjoiYWRtaW4tdGVzdCIsImV4cCI6MTY1NDg2NDkzMywianRpIjoiMGM2YTEyM2UtNmU4MC00ZWI4LWIyOGItYmFiYmRiYzIwMzQ0In0.xtpHoRRRekfUN4_HXo4OHN7bsUfDYL900WP8Zf9SFm0hE-KyISawmlubk5_97rA2SCSekr_v5Ux62tovf8Xaig";
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        System.out.println(claims.get("username"));
        System.out.println(claims.get("alg"));
        System.out.println(claims.getExpiration());
        System.out.println(claims.getId());

    }
}
