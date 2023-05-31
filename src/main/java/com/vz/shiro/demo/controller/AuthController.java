package com.vz.shiro.demo.controller;

import com.vz.shiro.demo.common.ErrCodeEnum;
import com.vz.shiro.demo.common.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * @author visy.wang
 * @description: 登录接口
 * @date 2023/5/30 15:06
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * 登录接口
     * @param account 账户名
     * @param password 密码
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<?> login(@RequestParam(value = "account") String account,
                           @RequestParam(value = "password") String password) {
        try {
            // 获取Subject对象
            Subject subject = SecurityUtils.getSubject();
            // 登录验证
            subject.login(new UsernamePasswordToken(account, password));
            // 能够执行到这步，肯定登录已经成功
            //将SessionId返回作为认证的Token
            return Result.success(subject.getSession().getId().toString());
        } catch (UnknownAccountException e) {
            return Result.error(ErrCodeEnum.ACCOUNT_NOT_EXIST);
        } catch (DisabledAccountException e) {
            return Result.error(ErrCodeEnum.ACCOUNT_IS_DISABLED);
        } catch (IncorrectCredentialsException e) {
            return Result.error(ErrCodeEnum.INCORRECT_CREDENTIALS);
        } catch (ExcessiveAttemptsException e){
            return Result.error(ErrCodeEnum.EXCESSIVE_ATTEMPTS);
        }catch (Throwable e) {
            e.printStackTrace();
            return Result.error(ErrCodeEnum.ERROR);
        }
    }

    /**
     * 退出登录
     * @return 退出结果
     */
    @GetMapping("/logout")
    public Result<?> logout(){
        //退出登录
        SecurityUtils.getSubject().logout();
        return Result.success();
    }

    @RequestMapping("/unauthorized")
    public Result<String> unauthorized(){
        return Result.error(ErrCodeEnum.UNAUTHORIZED);
    }
}
