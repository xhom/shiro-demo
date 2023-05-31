package com.vz.shiro.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author visy.wang
 * @description: 用户角色
 * @date 2023/5/30 13:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    private Long id;
    private Long userId;
    private Long roleId;

    //模拟数据
    public static final List<UserRole> DATA = new ArrayList<>();
    static {
        DATA.add(new UserRole(1L, 1L, 1L));
        DATA.add(new UserRole(2L, 2L, 2L));
        DATA.add(new UserRole(3L, 3L, 3L));
    }
}
/*
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `user_id` int(11) DEFAULT NULL,
 `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 2, 2);
INSERT INTO `user_role` VALUES (3, 3, 3);

SET FOREIGN_KEY_CHECKS = 1;
*/