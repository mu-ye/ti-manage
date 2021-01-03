package com.huan.demo.exception;

import lombok.NoArgsConstructor;

/**
 * @author mubaisama
 */
@NoArgsConstructor
public class TokenExpiredException extends BaseException{

    public TokenExpiredException(String message){
        super(message);
    }
}
