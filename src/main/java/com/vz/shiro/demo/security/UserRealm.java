package com.vz.shiro.demo.security;

import com.alibaba.fastjson.JSON;
import com.vz.shiro.demo.dao.PermissionMapper;
import com.vz.shiro.demo.dao.RoleMapper;
import com.vz.shiro.demo.dao.UserMapper;
import com.vz.shiro.demo.entity.Role;
import com.vz.shiro.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.*;

/**
 * @author visy.wang
 * @description: 负责认证用户身份和对用户进行授权
 * @date 2023/5/30 14:52
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {
    //用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        log.info("AuthorizationInfo-User: {}", JSON.toJSONString(user));
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roleList = RoleMapper.findRoleByUserId(user.getId());

        List<Long> roleIds = new ArrayList<>();
        Set<String> roleSet = new HashSet<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
            roleSet.add(role.getRole());
        }
        // 放入角色信息
        authorizationInfo.setRoles(roleSet);

        // 放入权限信息
        List<String> permissionList = PermissionMapper.findByRoleId(roleIds);
        authorizationInfo.setStringPermissions(new HashSet<>(permissionList));

        log.info("AuthorizationInfo: {}", JSON.toJSONString(authorizationInfo));
        return authorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        log.info("doGetAuthenticationInfo: {}", JSON.toJSONString(userToken));
        User user = UserMapper.findByAccount(userToken.getUsername());
        if (Objects.isNull(user)) {
            return null;
        }
        User principal = user.selfClone();
        principal.setPassword(null); //移除敏感信息
        return new SimpleAuthenticationInfo(principal, user.getPassword(), getName());
        //也可只在认证信息中保存用户名
        //将用户信息手动放到Session，并在已登陆时从Session中获取，注意在退出登录时清除掉用户信息
        //SecurityUtils.getSubject().getSession().setAttribute("LoginUser", principal);
        //return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    }
}
