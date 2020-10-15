/*
 Navicat Premium Data Transfer

 Source Server         : local_connect
 Source Server Type    : MySQL
 Source Server Version : 50647
 Source Host           : localhost:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 50647
 File Encoding         : 65001

 Date: 14/10/2020 19:06:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(13) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year` int(10) NULL DEFAULT NULL,
  `doi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `result` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `catalog` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'classification',
  `price` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (9, 'qqq', 'fsdc', 1999, 'vxcv', 'fsdfsdf', '2020-09-11T12:13', 'History', '200-300');
INSERT INTO `book` VALUES (10, 'xcvsetdfd', 'vxcvsdf', 1998, 'fsdfxcxv', 'vxcnfgh', '2020-09-11T12:14', 'Education', '200-300');
INSERT INTO `book` VALUES (11, 'qerwqetert', 'vcxv', 1998, 'vxcv', 'gdfgdfg', '2020-09-11T12:14', 'History', '100-200');
INSERT INTO `book` VALUES (12, 'ttttt', 'rwere', 1999, 'wwww', 'hghghg', '2020-09-19T12:14', 'History', '100-200');
INSERT INTO `book` VALUES (13, 'uuuuuuuuuuuuuuuuu', 'qqqqqqqqqqqq', 1999, 'mmmmmmmmmmmmmm', 'ggggggggggggggggggg', '2020-09-19T12:24', 'History', '100-200');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(13) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'aa', '123', 'users');
INSERT INTO `users` VALUES (2, 'admin', '123', 'administrator');

SET FOREIGN_KEY_CHECKS = 1;
