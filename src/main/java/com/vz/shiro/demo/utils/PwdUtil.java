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
    private static final int SALT_LEN = 16;
    private static final int SALT_PREFIX_LEN = 4;
    private static final SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
    /**
     * 随机获取一个盐值
     * @return 盐值
     */
    public static String getSalt(){
        return getSalt(SALT_LEN / 2);
    }
    public static String getSalt(int numBytes){
        return secureRandomNumberGenerator.nextBytes(numBytes).toHex();
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

    /**
     * 密码加密（自动加盐）
     * @param rawPassword 原密码（明文）
     * @return 密文
     */
    public static String encode(String rawPassword){
        return encode(rawPassword, 10);
    }
    /**
     * 密码加密（自动加盐）
     * @param rawPassword 原密码（明文）
     * @param strength 密码强度
     * @return 密文
     */
    public static String encode(String rawPassword,  int strength){
        String realSalt = getSalt(), saltPrefix = getSalt(SALT_PREFIX_LEN / 2);
        String strengthStr = (strength>9?"":"0") + strength;
        String salt = saltPrefix + strengthStr + realSalt;
        return hashpwd(rawPassword, salt);
    }

    private static String hashpwd(String rawPassword,  String salt){
        salt = salt.substring(0, SALT_PREFIX_LEN+2+SALT_LEN);
        int strength = Integer.parseInt(salt.substring(SALT_PREFIX_LEN, SALT_PREFIX_LEN+2));
        String realSalt = salt.substring(SALT_PREFIX_LEN+2);
        return salt + new Md5Hash(rawPassword, realSalt, strength).toHex();
    }

    /**
     * 密码对比，结合encrypt(String rawPassword,  int strength)使用
     * @param rawPassword 明文
     * @param encodedPassword 密文
     * @return 是否匹配
     */
    public static boolean  matches(String rawPassword, String encodedPassword){
        return encodedPassword.equals(hashpwd(rawPassword, encodedPassword));
    }
}
