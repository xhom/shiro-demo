package com.vz.shiro.demo.config;

import com.vz.shiro.demo.common.ErrCodeEnum;
import com.vz.shiro.demo.common.Result;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author visy.wang
 * @description: 用户异常处理器
 * @date 2023/5/30 14:50
 */
@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<?> UnAuthorizedExceptionHandler(UnauthorizedException e) {
        return Result.error(ErrCodeEnum.UNAUTHORIZED);
    }
}
