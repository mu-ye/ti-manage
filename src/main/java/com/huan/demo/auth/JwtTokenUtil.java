package com.huan.demo.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 牟欢
 * @Classname JwtTokenUtil
 * @Description TODO
 * @Date 2020-09-11 16:48
 */
@Slf4j
public class JwtTokenUtil {
    /**
     * 执行函数 initSecret() 生成的 SECRET
     */
    private static final String SECRET = "FJLDDk2qNJkSuqwT44hexMKiokoU6qcC3gLQCrZBcsY";
    /**
     * 负载中 自定义信息 KEY值
     */
    private static final String USER_INFO_KEY = "user_info";
    /**
     * token 的超时时间（单位为秒）1分钟
     */
    private static final long TOKEN_EXPIRED_SECOND = 1*24*60*60;

    /**
     *  点击记住我的后的 token超时时间为 2天
     */
    private static final long TOKEN_EXPIRED_SECOND_REMEMBER_ME = 2*24*60*60;

    /**
     * header中存放 token的字段名称
     */
    public static final String TOKEN_HEADER = "Authorization";
    /**
     * token 前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";


    /**
     * 生成 SECRET
     *
     * @return 返回BASE64 加密后的额 SECRET
     */
    public static void initSecret() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        log.info("SECRET:    {}", Encoders.BASE64.encode(key.getEncoded()));
    }

    /**
     * SecretKey 根据 SECRET 的编码方式解码后得到：
     * Base64 编码：SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
     * Base64URL 编码：SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretString));
     * 未编码：SecretKey key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
     */
    private static SecretKey getSecretKey() {
        byte[] encodeKey = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(encodeKey);
    }

    /**
     * 创建Token
     *
     * @param userName 用户名
     * @param isRememberMe 是否有记住我 true: token过期时间设为  TOKEN_EXPIRED_SECOND_REMEMBER_ME ，false oken过期时间设为  TOKEN_EXPIRED_SECOND
     * @return
     */
    public static String generateToken(String userName, Boolean isRememberMe) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put("sub", userName);
        claims.put("created", new Date());
        long expiration = isRememberMe ? TOKEN_EXPIRED_SECOND_REMEMBER_ME : TOKEN_EXPIRED_SECOND;
        // 添加自定义参数
        JwtBuilder jwtBuilder = Jwts.builder()
                // 添加负载
                .setClaims(claims)
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                // 设置 SECRET
                .signWith(getSecretKey());
        return jwtBuilder.compact();
    }

    /**
     * 从token中获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 验证令牌
     *
     * @param token 令牌
     * @param userName 用户名
     * @return
     */
    public static Boolean validateToken(String token, String userName) {
        String username = getUsernameFromToken(token);
        return (username.equals(userName) && !isTokenExpired(token));
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    // 解析 JWT 的服务器与创建 JWT 的服务器的时钟不一定完全同步，此设置允许两台服务器最多有 3 分钟的时差
                    .setAllowedClockSkewSeconds(180L)
                    .setSigningKey(getSecretKey())
                    .build().parseClaimsJws(token);
          claims = jws.getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public static void main(String[] args) {

        String token = generateToken("117042",true);
        log.info("--token:{}",token);
        String userName = getUsernameFromToken(token);
        log.info("--userName:{}",userName);
        log.info("--isLive:{}",isTokenExpired(token).toString());
        log.info("--yanzhenglingpai:{}",validateToken(token,"117042").toString());

        String token1 = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJlbnRyYW5jZSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNTk5ODA5OTMwLCJleHAiOjE1OTk4MTM1MzB9.LTMxjfAEiuX0zaPqmSHjVC1gnsyyzN4IXf34tYMhGnU";
        System.out.println(getUsernameFromToken(token1));
    }
}
