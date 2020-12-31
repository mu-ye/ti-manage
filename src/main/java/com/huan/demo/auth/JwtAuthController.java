package com.huan.demo.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 牟欢
 * @Classname JwtAuthController
 * @Description TODO
 * @Date 2020-09-16 16:48
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class JwtAuthController {

    private final JwtAuthService jwtAuthService;

    /**
     * 用户使用用户名密码登录，获取 jwtToken
     * <p>
     *     用户密码在数据库中使用 BCryptPasswordEncoder 加密
     * </p>
     *
     * @param loginParam 用户登录信息
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(@RequestBody LoginParam loginParam) {
        // 用户名密码不能为空
        if (StringUtils.isEmpty(loginParam.getUsername()) || StringUtils.isEmpty(loginParam.getPassword())) {
            throw new RuntimeException("用户名或密码不能为空");
        }
        // 如果 rememberMe 为空，rememberMe = false
        if(loginParam.getRememberMe() == null){
            loginParam.setRememberMe(false);
        }
        try {
            // 根据用户名密码生成 jwtToken
            return jwtAuthService.login(loginParam.getUsername(), loginParam.getPassword(),loginParam.getRememberMe());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
