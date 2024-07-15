/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : localhost:3306
 Source Schema         : glife

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 16/07/2024 00:29:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` bigint NOT NULL,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `permission` int NULL DEFAULT 1,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (12345, 'apple', '$2a$10$il.llR/gI1xei.YVO7xQAulNYIZ2J3b69Lu9Y2kBcZtS.x4wZdGc2', 2, '2024-07-15 19:52:23');
INSERT INTO `admin` VALUES (1812929133831729153, 'pear', 'bris12345', 1, '2024-07-15 20:16:03');
INSERT INTO `admin` VALUES (1812930274573365250, 'apple2', '$2a$10$y8QZFBqTG/Jk9g3Tk1QhZ.baTtI5ltxfl5gwB67h8WeBCpnIUjWim', 1, '2024-07-15 20:20:35');

SET FOREIGN_KEY_CHECKS = 1;
