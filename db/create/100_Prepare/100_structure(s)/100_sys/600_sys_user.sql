DROP TABLE IF EXISTS `oa_user`;
CREATE TABLE `oa_user`  (
  `UserId` varchar(36)  NOT NULL,
  `AddedTime` datetime(0) NULL DEFAULT NULL,
  `ModifiedTime` datetime(0) NULL DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT b'1',
  `LoginName` varchar(50)  NOT NULL,
  `Password` varchar(100)  NULL DEFAULT NULL,
  `UserName` varchar(50)  NOT NULL,
  `Email` varchar(50)  NULL DEFAULT NULL,
  `Phone` varchar(20)  NULL DEFAULT NULL,
  `IsHeader` bit(1) NOT NULL DEFAULT b'0',
  `DepartmentId` varchar(36)  NOT NULL,
  PRIMARY KEY (`UserId`) ,
  INDEX `fk_User_Department`(`DepartmentId`) ,
  CONSTRAINT `fk_User_Department` FOREIGN KEY (`DepartmentId`) REFERENCES `oa_department` (`DepartmentId`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='用户表';