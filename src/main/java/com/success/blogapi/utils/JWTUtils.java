package com.success.blogapi.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    private static final String jwtToken="onthewaytosuccess";

    public static  String createToken(Long userId){
        Map<String,Object> claims=new HashMap<>();
        claims.put("userId",userId);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtToken)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 20 * 60 * 60 * 60 * 1000));
        String token=jwtBuilder.compact();
        return token;

    }

    public static Map<String,Object> checkToken(String token){
        try{
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
            return (Map<String, Object>) parse.getBody();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String token = JWTUtils.createToken(1L);
        System.out.println(token);
        Map<String, Object> map = JWTUtils.checkToken(token);
        assert map!=null;
        System.out.println(map.get("userId"));
        System.out.println(DigestUtils.md2Hex("123456"+"hanhaimitu"));
    }
}
