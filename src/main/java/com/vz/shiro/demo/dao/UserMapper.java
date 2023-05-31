package com.vz.shiro.demo.dao;

import com.vz.shiro.demo.entity.User;

/**
 * @author visy.wang
 * @description: 用户Mapper
 * @date 2023/5/30 14:42
 */
public class UserMapper {
    public static User findByAccount(String account){
        return User.DATA.stream()
                .filter(u -> u.getAccount().equals(account))
                .findFirst()
                .orElse(null);
    }
}
