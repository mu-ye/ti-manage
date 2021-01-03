package com.huan.demo.result;

import com.huan.demo.exception.ExceptionEnum;
import com.huan.demo.exception.ReFreshTokenException;
import com.huan.demo.exception.TokenExpiredException;
import com.huan.demo.exception.UserNameNotMatchPasswordException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 异常处理
 *
 * @author RCNJTECH
 * @date 2020/6/22 16:30
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<String> handleNoHandlerFoundException(NoHandlerFoundException e) {
        return Result.error("1001", "API 不存在：" + e.getRequestURL());
    }

    @ExceptionHandler(BindException.class)
    public Result<String> handleBindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        String errorMessage = fieldErrorList.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("、"));
        return Result.error("1002", errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        String errorMessage = fieldErrorList.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("、"));
        return Result.error("1002", errorMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolationSet = e.getConstraintViolations();
        String errorMessage = constraintViolationSet.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("、"));
        return Result.error("1003", errorMessage);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return Result.error("1004", "缺少请求参数 " + e.getParameterName());

    }

    /**
     * 用户登录失败异常捕获，返回 http状态码 401
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserNameNotMatchPasswordException.class)
    public Result<String> handleUserNameNotMatchPasswordException(UserNameNotMatchPasswordException e) {
        return Result.error("401", "用户未认证 --" + e.getMessage());
    }

    /**
     * token 异常捕获，返回 http状态码 402
     * @param e
     * @return
     */
    @ResponseStatus( code = HttpStatus.PAYMENT_REQUIRED)
    @ExceptionHandler(TokenExpiredException.class)
    public Result<String> handleTokenExpiredException(TokenExpiredException e){
        return Result.error("402",e.getMessage());
    }

    /**
     * token 异常捕获，返回 http状态码 400
     * @param e
     * @return
     */
    @ResponseStatus( code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReFreshTokenException.class)
    public Result<String> ReFreshToke(ReFreshTokenException e){
        return Result.error("400",e.getMessage());
    }

    /**
     * 异常处理
     *
     * @param e Exception
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handle(Exception e) {
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return Result.error(ExceptionEnum.HttpRequestMethodNotSupportedException);
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            return Result.error(ExceptionEnum.MethodArgumentTypeMismatchException);
        }
        else {
            return Result.error("1001", e.getMessage());
        }
    }

}
