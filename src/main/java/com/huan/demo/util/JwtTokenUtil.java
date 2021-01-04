package com.huan.demo.util;

import com.huan.demo.exception.AccessTokenExpiredException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;

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
     * token 的超时时间（单位为秒）1天
     */
    private static final long TOKEN_EXPIRED_SECOND = 1 * 24 * 60 * 60;

    /**
     * 点击记住我的后的 token超时时间为 2天 (2 * 24 * 60 * 60)
     */
    private static final long TOKEN_EXPIRED_SECOND_REMEMBER_ME = 2 * 24 * 60 * 60;

    /**
     * header中存放 token的字段名称
     */
    public static final String TOKEN_HEADER = "Authorization";
    /**
     * token 前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String CLAIM_ROLES = "roles";


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
     * 创建 accessToken
     *
     * @param roles        用户拥有的权限
     * @param userName     用户名
     * @param isRememberMe 是否有记住我 true: token过期时间设为  TOKEN_EXPIRED_SECOND_REMEMBER_ME ，false oken过期时间设为  TOKEN_EXPIRED_SECOND
     * @return
     */
    public static String generateAccessToken(String userName, Boolean isRememberMe, List<String> roles) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put("sub", userName);
        claims.put("created", new Date());
        // 自定义负载
        claims.put(CLAIM_ROLES, roles);
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
     * 创建 refreshToken(和 accessToken相比，refreshToken没有用户权限信息，过期时间是accessToken两倍)
     *
     * @param userName     用户名
     * @param isRememberMe 是否记住我
     * @return
     */
    public static String generateReFreshToken(String userName, Boolean isRememberMe) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put("sub", userName);
        claims.put("created", new Date());
        // refreshToken 的有效时间是 accessToken 时间的两倍
        long expiration = (isRememberMe ? TOKEN_EXPIRED_SECOND_REMEMBER_ME : TOKEN_EXPIRED_SECOND) * 2;
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
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
        }
        return username;
    }

    /**
     * 从token中读取负载
     *
     * @param token
     * @return
     */
    public static List<String> getRolesFromToken(String token) {
        List<String> roles = null;
        try {
            Claims claims = getClaimsFromToken(token);
            roles = (List<String>) claims.get(CLAIM_ROLES);
        } catch (Exception e) {
        }
        return roles;
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
            return true;
        }
    }

    /*    */

    /**
     * 刷新token
     *
     * @param token
     */
    public static List<String> refreshAccessToken(String token) {
        List<String> tokenList = new ArrayList<>();
        tokenList.add(generateAccessToken(getUsernameFromToken(token), false, getRolesFromToken(token)));
        tokenList.add(generateReFreshToken(getUsernameFromToken(token), false));
        return tokenList;
    }


    /**
     * 验证令牌
     *
     * @param token    令牌
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
            log.info("token 已过期，抛出异常");
            throw new AccessTokenExpiredException();
        }
        return claims;
    }

    public static void main(String[] args) {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");

        String accessToken = generateAccessToken("117042", false, roles);
        System.out.println(accessToken);


        String refreshToken = generateReFreshToken("117042", false);
        System.out.println("refreshToken:" + refreshToken);

        System.out.println("getUsernameFromToken(accessToken):" + getUsernameFromToken(accessToken));
        System.out.println("getUsernameFromToken(refreshToken):" + getUsernameFromToken(refreshToken));

        System.out.println("getRolesFromToken(accessToken):" + getRolesFromToken(accessToken));
        System.out.println("getRolesFromToken(refreshToken):" + getRolesFromToken(refreshToken));
    }
}
