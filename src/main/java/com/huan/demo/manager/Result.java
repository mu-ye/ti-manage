package com.huan.demo.manager;

import lombok.Data;

/**
 * @author 牟欢
 * @Classname Result
 * @Description TODO
 * @Date 2020-12-08 10:07
 */
@Data
public class Result<T> {
    /**
     * 返回状态
     */
    public Boolean success;
    /**
     * 人员信息
     */
    public T data;
    /**
     * 错误码
     */
    public String errorCode;
    /**
     * 错误名
     */
    public String errorName;
    /**
     * 错误信息
     */
    public String errorMessage;
}
