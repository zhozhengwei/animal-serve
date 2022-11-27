package com.zzw.animalserve.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Date;
import java.util.Base64;
import java.util.UUID;

/**
 * @description(jwt工具类)
 * @autor: zhouzhengwei
 * @date: 2022/8/27__10:47
 */
public class JwtUtil {

    //有效时间
    public static final Long JWT_TTL = 60*60*1000L;// 一个小时

    //设置密钥明文
    public static final String JWT_KEY = "zzw";

    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-","");
        return token;
    }

    /***
    * @Description: 生成jwt
    * @Param: [subject] token中要存放的数据（json格式）
    * @return: java.lang.String
    * @Author: zhouzhengwei
    * @Date: 2022/8/27
    */
    public static String createJWT(String subject){
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    /**
    * @Description: 生成jwt
    * @Param: [subject, ttlMillis] token中要存放的数据（json格式）
    * @return: java.lang.String
    * @Author: zhouzhengwei
    * @Date: 2022/8/27
    */
    public static String createJWT(String subject, Long ttlMillis){
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis == null){
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid) //唯一的ID
                .setSubject(subject)    //主题 可以是JSON数据
                .setIssuer("sd")        //签发者
                .setIssuedAt(now)       //签发时间
                .signWith(signatureAlgorithm, secretKey)    //使用的转码的算法签名，第二个参数为密钥
                .setExpiration(expDate);
    }

    /**
    * @Description: 创建token
    * @Param: [id, subject, ttlMillis]
    * @return: java.lang.String
    * @Author: zhouzhengwei
    * @Date: 2022/8/27
    */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);     //设置过期时间
        return builder.compact();
    }

    /**
    * @Description: 生成加密后的密钥
    * @Param: []
    * @return: javax.crypto.SecretKey
    * @Author: zhouzhengwei
    * @Date: 2022/8/27
    */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    /**
    * @Description: 解析
    * @Param: [jwt]
    * @return: io.jsonwebtoken.Claims
    * @Author: zhouzhengwei
    * @Date: 2022/8/27
    */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static void main(String[] args) throws Exception {
        Claims claims = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5MmU4ZTAxYzk0ZjA0MmQyYTZiMDNjNDgxNzI5NDExYiIsInN1YiI6IjEiLCJpc3MiOiJzZCIsImlhdCI6MTY2MTYxNzc5MiwiZXhwIjoxNjYxNjIxMzkyfQ.AMJcdPRVeERRYnwmtbpumWFu7ZCBd_u8l3b1IGbXilM");
        String subject = claims.getSubject();
        System.out.println(subject);

    }



}
