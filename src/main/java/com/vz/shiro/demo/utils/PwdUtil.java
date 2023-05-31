package com.vz.shiro.demo.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * @author visy.wang
 * @description: 密码工具
 * @date 2023/5/30 18:16
 */
public class PwdUtil {
    private static final SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
    /**
     * 随机获取一个盐值
     * @return 盐值
     */
    public static String getSalt(){
        return secureRandomNumberGenerator.nextBytes().toHex();
    }

    /**
     * 密码加密
     * @param password 原密码（明文）
     * @param salt 盐值
     * @return 加密后的密码（密文） = SHA256( MD5(password) + salt )
     */
    public static String encrypt(String password, Object salt){
        return new Sha256Hash(new Md5Hash(password), salt, 2).toHex();
    }
}
