package com.vz.shiro.demo.dao;

import com.vz.shiro.demo.entity.Role;
import com.vz.shiro.demo.entity.UserRole;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author visy.wang
 * @description: 角色Mapper
 * @date 2023/5/30 14:38
 */
public class RoleMapper {
    public static List<Role> findRoleByUserId(Long userId){
        List<Long> roleIdList = UserRole.DATA.stream()
                .filter(ur -> ur.getUserId().equals(userId))
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
        return Role.DATA.stream()
                .filter(r -> roleIdList.contains(r.getId()))
                .collect(Collectors.toList());
    }
}
