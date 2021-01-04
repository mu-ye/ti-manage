package com.huan.demo.result;

import com.huan.demo.exception.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Result
 *
 * @author RCNJTECH
 * @date 2020/6/22 9:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 数据
     */
    private T data;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;


    public Result(Boolean success) {
        this.success = success;
    }

    private Result(Boolean success, T data) {
        this.success = success;
        this.data = data;
    }


    public static <T> Result<T> bool(Boolean success) {
        return new Result<>(success);
    }

    public static <T> Result<T> success() {
        return new Result<>(true);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, data);
    }

    public static <T> Result<T> error(String errorCode, String errorMessage) {
        return new Result<>(false, null, errorCode, errorMessage);
    }

    public static <T> Result<T> error(ExceptionEnum exceptionEnum) {
        return new Result<>(false, null, exceptionEnum.getErrorCode(), exceptionEnum.getErrorMessage());
    }

}
