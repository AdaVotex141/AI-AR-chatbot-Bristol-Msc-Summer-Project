/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80300 (8.3.0)
 Source Host           : localhost:3306
 Source Schema         : glife

 Target Server Type    : MySQL
 Target Server Version : 80300 (8.3.0)
 File Encoding         : 65001

 Date: 16/07/2024 14:45:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for badge
-- ----------------------------
DROP TABLE IF EXISTS `badge`;
CREATE TABLE `badge` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of badge
-- ----------------------------
BEGIN;
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (1, 'First Task Achiever Badge', 'Awarded for completing the first task.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (2, 'Daily Routine Starter Badge', 'Awarded for completing a daily routine for the first time.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (3, 'Routine Streak Master Badge', 'Awarded for maintaining a daily routine streak for 7 consecutive days.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (4, 'Monthly Routine Champion Badge', 'Awarded for consistently completing daily routines for a month.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (5, 'Personal Best Record Badge', 'Awarded for breaking a personal record in completing the daily routine.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (6, 'First Tree Planter Badge', 'Awarded for planting the first virtual tree in the AR forest.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (7, 'Green Thumb Master Badge', 'Awarded for planting 5 virtual trees.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (8, 'Eco Warrior Badge', 'Awarded for completing 10 eco-friendly tasks.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (9, 'Eco Mentor Badge', 'Awarded for sharing the link of your already attained badge.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (10, 'Challenge Accepted Badge', 'Awarded for completing the first random green challenge.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (11, 'Green Challenge Master Badge', 'Awarded for completing 10 random green challenges.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (12, 'Eco Milestone Badge', 'Awarded for showing continuous improvement in eco-friendly activities over time.');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
