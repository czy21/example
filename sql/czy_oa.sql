/*
 Navicat Premium Data Transfer

 Source Server         : localDb
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : localhost:3306
 Source Schema         : czy_oa

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : 65001

 Date: 15/10/2018 23:44:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oa_company
-- ----------------------------
DROP TABLE IF EXISTS `oa_company`;
CREATE TABLE `oa_company`  (
  `CompanyId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AddedTime` datetime(0) NULL DEFAULT NULL,
  `ModifiedTime` datetime(0) NULL DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT b'1',
  `Name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ContactPerson` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Location` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Fax` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Postcode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Comment` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`CompanyId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of oa_company
-- ----------------------------
INSERT INTO `oa_company` VALUES ('1a245e4d-cee6-11e8-8984-1cb72c963248', '2018-10-13 20:47:06', '2018-10-13 20:47:06', b'1', '思梦科技', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for oa_department
-- ----------------------------
DROP TABLE IF EXISTS `oa_department`;
CREATE TABLE `oa_department`  (
  `DepartmentId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AddedTime` datetime(0) NULL DEFAULT NULL,
  `ModifiedTime` datetime(0) NULL DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT b'1',
  `Name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ParentId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Comment` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CompanyId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`DepartmentId`) USING BTREE,
  INDEX `fk_Department_Company`(`CompanyId`) USING BTREE,
  CONSTRAINT `fk_Department_Company` FOREIGN KEY (`CompanyId`) REFERENCES `oa_company` (`CompanyId`) ON DELETE NO ACTION ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of oa_department
-- ----------------------------
INSERT INTO `oa_department` VALUES ('d760b860-cee6-11e8-8984-1cb72c963248', '2018-10-13 20:52:23', '2018-10-13 20:52:23', b'1', '研发部', '00000000-0000-0000-0000-000000000000', '15145033859', NULL, '1a245e4d-cee6-11e8-8984-1cb72c963248');

-- ----------------------------
-- Table structure for oa_menu
-- ----------------------------
DROP TABLE IF EXISTS `oa_menu`;
CREATE TABLE `oa_menu`  (
  `MenuId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AddedTime` datetime(0) NULL DEFAULT NULL,
  `ModifiedTime` datetime(0) NULL DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT b'1',
  `ParentId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Sort` int(11) NOT NULL DEFAULT 0,
  `Url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IsMenu` bit(1) NOT NULL DEFAULT b'0',
  `Comment` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`MenuId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of oa_menu
-- ----------------------------
INSERT INTO `oa_menu` VALUES ('a25c2553-cfab-11e8-b266-1cb72c963248', '2018-10-14 20:21:09', '2018-10-14 20:21:09', b'1', '00000000-0000-0000-0000-000000000000', '系统管理', NULL, 0, '#', b'1', NULL);

-- ----------------------------
-- Table structure for oa_role
-- ----------------------------
DROP TABLE IF EXISTS `oa_role`;
CREATE TABLE `oa_role`  (
  `RoleId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AddedTime` datetime(0) NULL DEFAULT NULL,
  `ModifiedTime` datetime(0) NULL DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT b'1',
  `Name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Comment` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`RoleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of oa_role
-- ----------------------------
INSERT INTO `oa_role` VALUES ('b7118e52-d082-11e8-958a-1cb72c963248', '2018-10-15 22:00:45', '2018-10-15 22:00:45', b'1', '测试角色', NULL);

-- ----------------------------
-- Table structure for oa_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `oa_role_menu`;
CREATE TABLE `oa_role_menu`  (
  `RoleMenuId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AddedTime` datetime(0) NULL DEFAULT NULL,
  `ModifiedTime` datetime(0) NULL DEFAULT NULL,
  `RoleId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MenuId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`RoleMenuId`) USING BTREE,
  INDEX `fk_RoleMenu_Menu`(`MenuId`) USING BTREE,
  INDEX `fk_RoleMenu_Role`(`RoleId`) USING BTREE,
  CONSTRAINT `fk_RoleMenu_Menu` FOREIGN KEY (`MenuId`) REFERENCES `oa_menu` (`MenuId`) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT `fk_RoleMenu_Role` FOREIGN KEY (`RoleId`) REFERENCES `oa_role` (`RoleId`) ON DELETE NO ACTION ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of oa_role_menu
-- ----------------------------
INSERT INTO `oa_role_menu` VALUES ('c65fe99b-d08e-11e8-958a-1cb72c963248', '2018-10-15 23:27:04', '2018-10-15 23:27:04', 'b7118e52-d082-11e8-958a-1cb72c963248', 'a25c2553-cfab-11e8-b266-1cb72c963248');

-- ----------------------------
-- Table structure for oa_user
-- ----------------------------
DROP TABLE IF EXISTS `oa_user`;
CREATE TABLE `oa_user`  (
  `UserId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AddedTime` datetime(0) NULL DEFAULT NULL,
  `ModifiedTime` datetime(0) NULL DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT b'1',
  `LoginName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UserName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IsHeader` bit(1) NOT NULL DEFAULT b'0',
  `DepartmentId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`UserId`) USING BTREE,
  INDEX `fk_User_Department`(`DepartmentId`) USING BTREE,
  CONSTRAINT `fk_User_Department` FOREIGN KEY (`DepartmentId`) REFERENCES `oa_department` (`DepartmentId`) ON DELETE NO ACTION ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of oa_user
-- ----------------------------
INSERT INTO `oa_user` VALUES ('1e6b2317-cee7-11e8-8984-1cb72c963248', '2018-10-13 20:54:22', '2018-10-13 20:54:22', b'1', 'admin', 'admin', '陈昭宇', '805899926@qq.com', NULL, b'0', 'd760b860-cee6-11e8-8984-1cb72c963248');

-- ----------------------------
-- Table structure for oa_user_role
-- ----------------------------
DROP TABLE IF EXISTS `oa_user_role`;
CREATE TABLE `oa_user_role`  (
  `UserRoleId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AddedTime` datetime(0) NULL DEFAULT NULL,
  `ModifiedTime` datetime(0) NULL DEFAULT NULL,
  `UserId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `RoleId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`UserRoleId`) USING BTREE,
  INDEX `fk_UserRole_Role`(`RoleId`) USING BTREE,
  INDEX `fk_UserRole_User`(`UserId`) USING BTREE,
  CONSTRAINT `fk_UserRole_Role` FOREIGN KEY (`RoleId`) REFERENCES `oa_role` (`RoleId`) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT `fk_UserRole_User` FOREIGN KEY (`UserId`) REFERENCES `oa_user` (`UserId`) ON DELETE NO ACTION ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of oa_user_role
-- ----------------------------
INSERT INTO `oa_user_role` VALUES ('e8f66a9c-d082-11e8-958a-1cb72c963248', '2018-10-15 22:02:08', '2018-10-15 22:02:08', '1e6b2317-cee7-11e8-8984-1cb72c963248', 'b7118e52-d082-11e8-958a-1cb72c963248');

SET FOREIGN_KEY_CHECKS = 1;
