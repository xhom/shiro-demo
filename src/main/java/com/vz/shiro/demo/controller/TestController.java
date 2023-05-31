package com.vz.shiro.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.vz.shiro.demo.common.ErrCodeEnum;
import com.vz.shiro.demo.common.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author visy.wang
 * @description: 测试接口
 * @date 2023/5/30 20:10
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/login")
    public Result<?> login() {
        return Result.error(ErrCodeEnum.NOT_LOGIN_IN);
    }

    @GetMapping("/auth")
    public Result<String> auth(){
        Subject subject = SecurityUtils.getSubject();
        System.out.println("是否已登录："+ subject.isAuthenticated());
        Object principal = subject.getPrincipal();
        System.out.println("principal: "+ JSON.toJSONString(principal, SerializerFeature.WriteMapNullValue));
        return Result.success("已成功登录");
    }

    @GetMapping("/notLogin")
    public Result<String> notLogin() {
        return Result.success("我不需要登录");
    }

    @GetMapping("/role")
    @RequiresRoles("vip")
    public Result<String> role() {
        return Result.success("测试Vip角色");
    }

    @GetMapping("/permission")
    @RequiresPermissions(value = {"add", "update"}, logical = Logical.AND)
    public Result<String> permission() {
        return Result.success("测试Add和Update权限");
    }
}
