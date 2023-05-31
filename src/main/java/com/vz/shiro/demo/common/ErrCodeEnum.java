package com.vz.shiro.demo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author visy.wang
 * @description: 错误码枚举
 * @date 2023/5/30 14:45
 */
@Getter
@AllArgsConstructor
public enum ErrCodeEnum {
    SUCCESS(0, "成功"),
    ERROR(10, "失败"),
    ACCOUNT_NOT_EXIST(11, "账号不存在"),
    DUPLICATE_ACCOUNT(12, "账号重复"),
    ACCOUNT_IS_DISABLED(13, "账号被禁用"),
    INCORRECT_CREDENTIALS(14, "账号或密码错误"),
    NOT_LOGIN_IN(15, "账号未登录"),
    UNAUTHORIZED(16, "没有权限"),
    EXCESSIVE_ATTEMPTS(17, "请求过于频繁");

    private final Integer code;
    private final String message;
}
