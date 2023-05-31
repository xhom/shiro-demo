package com.vz.shiro.demo.dao;

import com.vz.shiro.demo.entity.Permission;
import com.vz.shiro.demo.entity.RolePermission;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author visy.wang
 * @description: 权限Mapper
 * @date 2023/5/30 14:31
 */
public class PermissionMapper {
    public static List<String> findByRoleId(List<Long> roleIds){
        List<Long> permissionIdList = RolePermission.DATA.stream()
                .filter(rp -> roleIds.contains(rp.getRoleId()))
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toList());
        return Permission.DATA.stream()
                .filter(p -> permissionIdList.contains(p.getId()))
                .map(Permission::getPermission)
                .collect(Collectors.toList());

    }
}
