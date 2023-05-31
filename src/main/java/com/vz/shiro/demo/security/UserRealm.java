package com.vz.shiro.demo.security;

import com.alibaba.fastjson.JSON;
import com.vz.shiro.demo.dao.PermissionMapper;
import com.vz.shiro.demo.dao.RoleMapper;
import com.vz.shiro.demo.dao.UserMapper;
import com.vz.shiro.demo.entity.Role;
import com.vz.shiro.demo.entity.User;
import org.apache.shiro.SecurityUtils;
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
public class UserRealm extends AuthorizingRealm {
    //用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        System.out.println("AuthorizationInfo-User:" + JSON.toJSONString(user));
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

        System.out.println("AuthorizationInfo:" + JSON.toJSONString(authorizationInfo));
        return authorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        System.out.println("doGetAuthenticationInfo: "+userToken.getUsername());
        User user = UserMapper.findByAccount(userToken.getUsername());
        if (Objects.isNull(user)) {
            return null;
        }
        String password = user.getPassword();
        //移除敏感信息
        //user.setPassword(null);
        return new SimpleAuthenticationInfo(user, password, getName());
    }
}
