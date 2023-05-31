package com.vz.shiro.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author visy.wang
 * @description: 角色
 * @date 2023/5/30 13:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Long id;
    private String role;
    private String desc;

    //模拟数据
    public static final List<Role> DATA = new ArrayList<>();
    static {
        DATA.add(new Role(1L, "admin", "超级管理员"));
        DATA.add(new Role(2L, "user", "普通用户"));
        DATA.add(new Role(3L, "vip_user", "VIP用户"));
    }
}
/*
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `role` varchar(255) DEFAULT NULL,
 `desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '超级管理员');
INSERT INTO `role` VALUES (2, 'user', '普通用户');
INSERT INTO `role` VALUES (3, 'vip_user', 'VIP用户');

SET FOREIGN_KEY_CHECKS = 1;
*/