package com.huan.demo.exception;

/**
 * <p>
 * 自定义异常状态码（errorCode）和错误信息（errorMessage）
 * 在统一异常处理中的 result->GlobalExceptionHandler中使用
 * </p>
 *
 * @author 牟欢
 * @Classname ExceptionEnum
 * @Description TODO
 * @Date 2020-09-21 15:22
 */

public enum ExceptionEnum {

    /**
     * 用户名密码登录失败
     */
    UserNameNotMatchPasswordException("4010", "用户名或密码错误，请重新登录"),

    /**
     * accessToken 过期， refreshToken 未过期
     */
    AccessTokenExpiredException("4011", "accessToken已过期，请获取新 accessToken 和 refreshToken"),

    /**
     * refreshToken 过期
     */
    RefreshTokenExpiredException("4012", "用户账号已过期，请重新登录"),

    /**
     * 请求方法不受支持
     */
    HttpRequestMethodNotSupportedException("A0101", "请求方法不受支持"),

    /**
     * 请求方法参数类型错误
     */
    MethodArgumentTypeMismatchException("A0102", "请求方法参数类型错误");

    /**
     * 错误码 (枚举类参数1)
     */
    private final String errorCode;

    /**
     * 错误信息（枚举类参数2）
     */
    private final String errorMessage;

    /**
     * 枚举类自定义构造函数（全参构造函数）
     *
     * @param errorCode
     * @param errorMessage
     */
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

    /**
     * <p> 枚举类学习</p>
     *
     * <p>
     *     1. 第一行显示的列出所有枚举实例，系统会自动使用 public static final 修饰。
     *     2. enum 可以有自己的成员变量、成员方法、默认构造器（不能使用public 修饰、可以使用private 也可以不使用修饰符）。
     *     3. [.compareTo] 比较顺序  [>0] 第一个枚举对象位置在后  ； [=0] 相等 ； [<0] 第一个枚举对象位置在前。
     *     4. [.ordinal()] 枚举索引，从 [0] 开始。
     *     5. [.values()] 遍历所有枚举
     *
     *     6. 包含抽象方法的枚举类： https://www.jianshu.com/p/46dbd930f6a2
     */

    public static void main(String[] args) {
        // 3.
        System.out.println(ExceptionEnum.AccessTokenExpiredException.compareTo(ExceptionEnum.UserNameNotMatchPasswordException));
        System.out.println(ExceptionEnum.AccessTokenExpiredException.compareTo(ExceptionEnum.AccessTokenExpiredException));
        System.out.println(ExceptionEnum.AccessTokenExpiredException.compareTo(ExceptionEnum.MethodArgumentTypeMismatchException));
        // 4.
        System.out.println(ExceptionEnum.AccessTokenExpiredException.ordinal());
        // 5.
        for(ExceptionEnum exceptionEnum : ExceptionEnum.values()){
            System.out.println(exceptionEnum.toString());
        }
    }


}
