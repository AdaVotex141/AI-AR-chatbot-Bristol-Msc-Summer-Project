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

 Date: 25/07/2024 17:54:52
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
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (1, 'First Task Achiever Badge', 'Awarded for completing the first random task.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (2, 'Daily Routine Starter Badge', 'Awarded for completing all the tasks in the daily routine for the first time.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (3, 'Routine Streak Master Badge', 'Awarded for logging in for 7 consecutive days.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (4, 'Monthly Routine Champion Badge', 'Awarded for logging in for 30 consecutive days.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (5, 'Quarterly Routine Champion Badge', 'Awarded for logging in for 90 consecutive days.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (6, 'Yearly Routine Champion Badge', 'Awarded for logging in for 365 consecutive days.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (7, 'First Tree Planter Badge', 'Awarded for planting the first virtual tree in the AR forest.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (8, 'Green Thumb Master Badge', 'Awarded for planting 5 virtual trees.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (9, 'Weekly Warrior Badge', 'Awarded for completing a weekly routine for the first time.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (10, 'Monthly Mastermind Badge', 'Awarded for completing a monthly routine for the first time.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (11, 'Green Challenge Master Badge', 'Awarded for adding 5 routines from random tasks or AI chatbot.');
INSERT INTO `badge` (`id`, `name`, `description`) VALUES (12, 'Eco Milestone Badge', 'Awarded for adding 10 routines from random tasks or AI chatbot.');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
