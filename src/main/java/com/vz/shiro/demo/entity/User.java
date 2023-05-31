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
        DATA.forEach(item -> {
            System.out.println(JSON.toJSONString(item));
        });
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
 `password` varchar(255) DEFAULT NULL,
 `username` varchar(255) DEFAULT NULL,
 `account` varchar(255) DEFAULT NULL,
 `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', '超级用户', 'root', 'salt123654');
INSERT INTO `user` VALUES (2, 'user', '普通用户', 'user', 'salt654321');
INSERT INTO `user` VALUES (3, 'vip', 'VIP用户', 'vip', 'salt123456');

SET FOREIGN_KEY_CHECKS = 1;
*/
