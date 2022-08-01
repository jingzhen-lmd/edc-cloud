/*
 * @ 版权所有(C)，上海海鼎信息工程股份有限公司，2021，所有权利保留。
 * @ 项目名：	studyplus
 * @ 文件名：
 * @ 模块说明：
 * @ 修改历史：
 * @ 2022/7/28- jingzhen - 创建。
 */
package com.edcccd.gateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyTokenUtil {

    //荷载中用户名
    private static final String CLAIM_KEY_USERNAME = "sub";
    //荷载中用户id
    private static final String CLAIM_KEY_USERID = "jti";
    //jwt的创建时间
    private static final String CLAIM_KEY_CREATE = "created";

    //密钥
    @Value("${jwt.secret}")
    private String secret;
    //失效时间
    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * 根据User的id生成token
     */
    public String generateToken(String userId) {
        //将用户名和信息放入map中，
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERID, userId);
        claims.put(CLAIM_KEY_CREATE, new Date());
        //将map加密，作为荷载返回token
        return generateToken(claims);
    }

    /**
     * 根据User的id生成token
     */
    public String generateToken(Long userId) {
        return generateToken(userId.toString());
    }

    /**
     * 根据claims信息生成JWT Token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims)  //放入荷载
                   .setExpiration(generateExpirationDate())    //设置失效时间
                   .signWith(SignatureAlgorithm.HS256, secret)  //设置签名
                   .compact();
    }

    /**
     * 生成token失效的时间 当前的时间+设置的时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取用户id
     */
    public String getUserIdFromToken(String token) {
        try {
            Claims claims = getClaimFromToken(token);
            return claims.getId();
        } catch (Exception e) {
            // todo 异常则不取出,打印日志
            System.out.println("token获取id异常" + e.getMessage());
        }
        return "token获取id异常";
    }

    /**
     * 从token中取出荷载
     */
    private Claims getClaimFromToken(String token) {
        Claims claims = Jwts.parser()    //调用转换方法
                            .setSigningKey(secret)  //放入密钥
                            .parseClaimsJws(token)  //放入需要转换的token
                            .getBody(); //取出荷载
        return claims;
    }

    /**
     * 判断token是否有效
     */
    public boolean validateToken(String token, String userId) {
        String id = getUserIdFromToken(token);
        return id.equals(userId) && !isTokenExpired(token);
    }

    /**
     * 刷新token有效时间 放入当前时间即可
     */
    public String refreshToken(String token) {
        Claims claims = getClaimFromToken(token);
        claims.put(CLAIM_KEY_CREATE, new Date());
        return generateToken(claims);
    }

    /**
     * 验证是否被刷新,过期则可以刷新
     */
    public boolean canRefresh(String token) {
        return isTokenExpired(token);
    }

    /**
     * 判断是否过期失效
     */
    public boolean isTokenExpired(String token) {
        //取出荷载中的失效时间
        Date expireDate = getClaimFromToken(token).getExpiration();
        return expireDate.before(new Date());
    }
}
