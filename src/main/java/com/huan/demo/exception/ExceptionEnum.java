package com.huan.demo.exception;

/**
 * @author 牟欢
 * @Classname ExceptionEnum
 * @Description TODO
 * @Date 2020-09-21 15:22
 */
public enum ExceptionEnum {
    /**
     *  用户名密码登录失败
     */
    UserNameNotMatchPasswordException("4010","用户名或密码错误，请重新登录"),

    /**
     * accessToken 过期， refreshToken 未过期
     */
    AccessTokenExpiredException("4011","accessToken已过期，请获取新 accessToken 和 refreshToken"),

    /**
     * refreshToken 过期
     */
    RefreshTokenExpiredException("4012","用户账号已过期，请重新登录"),

    /**
     * 请求方法不受支持
     */
    HttpRequestMethodNotSupportedException("A0101", "请求方法不受支持"),

    /**
     * 请求方法参数类型错误
     */
    MethodArgumentTypeMismatchException("A0102", "请求方法参数类型错误");







    /**
     * 错误码
     */
    private final String errorCode;

    /**
     * 错误信息
     */
    private final String errorMessage;

    ExceptionEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
