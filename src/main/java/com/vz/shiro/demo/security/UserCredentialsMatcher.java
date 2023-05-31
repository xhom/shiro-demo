package com.vz.shiro.demo.security;

import com.vz.shiro.demo.entity.User;
import com.vz.shiro.demo.utils.PwdUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author visy.wang
 * @description: 自定义密码比较器
 * @date 2023/5/30 20:55
 */
public class UserCredentialsMatcher extends SimpleCredentialsMatcher {
    /**
     * 密码比较器
     * @param token 前端用户信息
     * @param info  数据库用户信息
     * @return 密码比较结果
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        try {
            //获取用户输入的用户名和密码
            UsernamePasswordToken userToken = (UsernamePasswordToken) token;
            //得到密码凭证并转换为String类型
            String password = new String(userToken.getPassword());
            //获取数据库用户信息
            User user = (User) info.getPrincipals().getPrimaryPrincipal();
            //将用户输入的密码转为密文(Tips：数据库保存密码时需采用同样的加密规则)
            password = PwdUtil.encrypt(password, user.getSalt());
            //获取数据库中的密文密码
            Object credentials = info.getCredentials();
            //密码验证
            return equals(password, credentials);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
