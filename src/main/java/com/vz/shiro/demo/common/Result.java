package com.vz.shiro.demo.common;

import lombok.Data;

/**
 * @author visy.wang
 * @description: 通用返回体
 * @date 2023/5/30 14:46
 */
@Data
public class Result<T> {
    // 响应码
    private Integer code;
    // 描述信息
    private String message;
    // 响应内容
    private T data;

    public Result() {}
    private Result(ErrCodeEnum errCodeEnum) {
        this.code = errCodeEnum.getCode();
        this.message = errCodeEnum.getMessage();
    }
    private Result(ErrCodeEnum errCodeEnum, T data) {
        this.code = errCodeEnum.getCode();
        this.message = errCodeEnum.getMessage();
        this.data = data;
    }
    private Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> Result<T> success() {
        return new Result<>(ErrCodeEnum.SUCCESS);
    }
    public static <T> Result<T> success(T data) {
        return new Result<>(ErrCodeEnum.SUCCESS, data);
    }
    public static <T> Result<T> error(ErrCodeEnum errCodeEnum) {
        return error(errCodeEnum.getCode(), errCodeEnum.getMessage());
    }
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message);
    }
}
