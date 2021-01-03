package com.huan.demo.exception;

import lombok.NoArgsConstructor;

/**
 * @author mubaisama
 */
@NoArgsConstructor
public class ReFreshTokenException extends BaseException{

    public ReFreshTokenException(String message){
        super(message);
    }
}
