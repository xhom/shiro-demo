package com.vz.shiro.demo.entity;

import com.alibaba.fastjson.JSON;
import com.vz.shiro.demo.utils.PwdUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author visy.wang
 * @description: 用户
 * @date 2023/5/30 13:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String account;
    private String password;
    private String salt;

    //模拟数据
    public static final List<User> DATA = new ArrayList<>();
    static {
        String salt1 = PwdUtil.getSalt(), salt2 = PwdUtil.getSalt(), salt3 = PwdUtil.getSalt();
        DATA.add(new User(1L, "超级用户", "root", PwdUtil.encrypt("123456", salt1), salt1));
        DATA.add(new User(2L, "普通用户", "user", PwdUtil.encrypt("abc123", salt2), salt2));
        DATA.add(new User(3L, "VIP用户", "vip", PwdUtil.encrypt("123abc", salt3), salt3));
        System.out.println("========================用户列表=================================");
        DATA.forEach(item -> System.out.println(JSON.toJSONString(item)));
        System.out.println("===============================================================");
    }
}
/*
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
 `id` bigint(20) NOTNULL AUTO_INCREMENT,
 `username` varchar(255) DEFAULT NULL,
 `account` varchar(255) DEFAULT NULL,
 `password` varchar(255) DEFAULT NULL,
 `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '超级用户', 'root', '8a97e77125feecc097dbc4691e99bec274f2e49ab75dba32a0b4ed6972c9c8b3', '7b692a2a6f9f107e66ecb1b36d9c774e');
INSERT INTO `user` VALUES (2, '普通用户', 'user', '73806194229ed6893556760d688018b31852c26afa5e058abc4a43fc088c3447', '18ca912e43b198b2b4d1dd9a236dbb0b');
INSERT INTO `user` VALUES (3, 'VIP用户', 'vip', 'd94f7e8c50201a700a43ce0576827316a560bd1bd96e7a6b67ed0f059084460d', '099d13bdb973c9200aa4d077a30482dc');

SET FOREIGN_KEY_CHECKS = 1;
*/
