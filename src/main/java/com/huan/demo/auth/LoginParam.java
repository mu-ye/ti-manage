package com.huan.demo.auth;

import lombok.Data;

/**
 * @author 牟欢
 * @Classname LoginParam
 * @Description TODO
 * @Date 2020-12-15 15:55
 */
@Data
public class LoginParam {
    /**
     *  用户名
     */
    public String username;
    /**
     * 密码
     */
    public String password;
    /**
     * 记住我
     */
    public Boolean rememberMe;
}
