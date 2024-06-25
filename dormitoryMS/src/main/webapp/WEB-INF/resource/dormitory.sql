/*
 Navicat Premium Data Transfer

 Source Server         : dormitoryMS
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : localhost:3306
 Source Schema         : dormitory

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 25/06/2024 23:52:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dormitories
-- ----------------------------
DROP TABLE IF EXISTS `dormitories`;
CREATE TABLE `dormitories`  (
  `dormitoryName` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '宿舍名',
  `totalBeds` int NULL DEFAULT NULL COMMENT '总床位',
  `availableBeds` int NULL DEFAULT NULL COMMENT '剩余床位',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dormitoryName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitories
-- ----------------------------
INSERT INTO `dormitories` VALUES ('224寝室', 4, 0, '男寝');
INSERT INTO `dormitories` VALUES ('225寝室', 4, 4, '男寝');
INSERT INTO `dormitories` VALUES ('226寝室', 4, 4, '男寝');
INSERT INTO `dormitories` VALUES ('228寝室', 4, 4, '男寝');

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students`  (
  `stuNo` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '学号',
  `name` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `dormitoryName` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '宿舍名',
  PRIMARY KEY (`stuNo`) USING BTREE,
  INDEX `dormitoryName`(`dormitoryName` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('2202203781', '王伟', '男', 26, '224寝室');
INSERT INTO `students` VALUES ('2202203784', '祝辉意', '男', 26, '224寝室');
INSERT INTO `students` VALUES ('2202203787', '徐超', '男', 26, '224寝室');
INSERT INTO `students` VALUES ('2202203789', '曾志成', '男', 26, '224寝室');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`username`, `password`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('admin', '123');

SET FOREIGN_KEY_CHECKS = 1;
