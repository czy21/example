DROP TABLE IF EXISTS `oa_user_role`;
CREATE TABLE `oa_user_role`  (
  `UserRoleId` varchar(36)  NOT NULL,
  `AddedTime` datetime(0) NULL DEFAULT NULL,
  `ModifiedTime` datetime(0) NULL DEFAULT NULL,
  `UserId` varchar(36)  NOT NULL,
  `RoleId` varchar(36)  NOT NULL,
  PRIMARY KEY (`UserRoleId`) ,
  INDEX `fk_UserRole_Role`(`RoleId`) ,
  INDEX `fk_UserRole_User`(`UserId`) ,
  CONSTRAINT `fk_UserRole_Role` FOREIGN KEY (`RoleId`) REFERENCES `oa_role` (`RoleId`) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT `fk_UserRole_User` FOREIGN KEY (`UserId`) REFERENCES `oa_user` (`UserId`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='用户角色表';