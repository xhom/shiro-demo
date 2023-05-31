package com.vz.shiro.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author visy.wang
 * @description: 权限
 * @date 2023/5/30 13:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    private Long id;
    private String permission;
    private String desc;

    //模拟数据
    public static final List<Permission> DATA = new ArrayList<>();
    static {
        DATA.add(new Permission(1L, "add", "增加"));
        DATA.add(new Permission(2L, "update", "更新"));
        DATA.add(new Permission(3L, "select", "查看"));
        DATA.add(new Permission(4L, "delete", "删除"));
    }
}
/*
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `permission `varchar(255) DEFAULT NULL COMMENT '权限名称',
 `desc` varchar(255) DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 'add', '增加');
INSERT INTO `permission` VALUES (2, 'update', '更新');
INSERT INTO `permission` VALUES (3, 'select', '查看');
INSERT INTO `permission` VALUES (4, 'delete', '删除');

SET FOREIGN_KEY_CHECKS = 1;
*/
