CREATE TABLE `sys_user`  (
  `UserId` varchar(36)  NOT NULL,
  `LoginName` varchar(50)  NOT NULL COMMENT '用户账号',
  `Password` varchar(100)  NULL DEFAULT NULL COMMENT '用户密码',
  `UserName` varchar(50)  NOT NULL COMMENT '用户姓名',
  `Email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `Phone` varchar(20) DEFAULT NULL COMMENT '用户电话',
  `IsHeader` bit(1) NOT NULL DEFAULT 0 COMMENT '是否是部长',
  `DepartmentId` varchar(36)  NOT NULL COMMENT '所属部门',
  `AddedTime` datetime(0) DEFAULT NULL,
  `ModifiedTime` datetime(0) DEFAULT NULL,
  `AddedUser` varchar(36) DEFAULT NULL,
  `ModifiedUser` varchar(36) DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`UserId`) ,
  INDEX `fk_User_Department`(`DepartmentId`) ,
  CONSTRAINT `fk_User_Department` FOREIGN KEY (`DepartmentId`) REFERENCES `sys_department` (`DepartmentId`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='用户表';