package com.huan.demo.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author mubaisama
 */
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
