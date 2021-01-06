package com.huan.demo.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "用户名不能为空")
    public String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    public String password;
    /**
     * 记住我
     */
    public Boolean rememberMe;
}
