CREATE TABLE `oa_user`  (
  `UserId` varchar(36)  NOT NULL,
  `LoginName` varchar(50)  NOT NULL,
  `Password` varchar(100)  NULL DEFAULT NULL,
  `UserName` varchar(50)  NOT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `IsHeader` bit(1) NOT NULL DEFAULT 0,
  `DepartmentId` varchar(36)  NOT NULL,
  `AddedTime` datetime(0) DEFAULT NULL,
  `ModifiedTime` datetime(0) DEFAULT NULL,
  `AddedUser` varchar(36) DEFAULT NULL,
  `ModifiedUser` varchar(36) DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`UserId`) ,
  INDEX `fk_User_Department`(`DepartmentId`) ,
  CONSTRAINT `fk_User_Department` FOREIGN KEY (`DepartmentId`) REFERENCES `oa_department` (`DepartmentId`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='用户表';