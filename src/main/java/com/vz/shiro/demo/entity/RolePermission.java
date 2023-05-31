package com.vz.shiro.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author visy.wang
 * @description: 角色权限
 * @date 2023/5/30 13:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission {
    private Long id;
    private Long roleId;
    private Long permissionId;

    //模拟数据
    public static final List<RolePermission> DATA = new ArrayList<>();
    static {
        DATA.add(new RolePermission(1L, 1L, 1L));
        DATA.add(new RolePermission(2L, 1L, 2L));
        DATA.add(new RolePermission(3L, 1L, 3L));
        DATA.add(new RolePermission(4L, 1L, 4L));
        DATA.add(new RolePermission(5L, 2L, 3L));
        DATA.add(new RolePermission(6L, 3L, 4L));
        DATA.add(new RolePermission(7L, 3L, 2L));
        DATA.add(new RolePermission(8L, 2L, 1L));
    }
}
/*
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `role_id` int(11) DEFAULT NULL,
 `permission_id` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1, 1);
INSERT INTO `role_permission` VALUES (2, 1, 2);
INSERT INTO `role_permission` VALUES (3, 1, 3);
INSERT INTO `role_permission` VALUES (4, 1, 4);
INSERT INTO `role_permission` VALUES (5, 2, 3);
INSERT INTO `role_permission` VALUES (6, 3, 3);
INSERT INTO `role_permission` VALUES (7, 3, 2);
INSERT INTO `role_permission` VALUES (8, 2, 1);

SET FOREIGN_KEY_CHECKS = 1;
*/