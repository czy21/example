CREATE TABLE `oa_user_role`  (
  `UserRoleId` varchar(36)  NOT NULL,
  `UserId` varchar(36)  NOT NULL,
  `RoleId` varchar(36)  NOT NULL,
  `AddedTime` datetime(0) DEFAULT NULL,
  `ModifiedTime` datetime(0) DEFAULT NULL,
  `AddedUser` varchar(36) DEFAULT NULL,
  `ModifiedUser` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`UserRoleId`) ,
  INDEX `fk_UserRole_Role`(`RoleId`) ,
  INDEX `fk_UserRole_User`(`UserId`) ,
  CONSTRAINT `fk_UserRole_Role` FOREIGN KEY (`RoleId`) REFERENCES `oa_role` (`RoleId`) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT `fk_UserRole_User` FOREIGN KEY (`UserId`) REFERENCES `oa_user` (`UserId`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='用户角色表';