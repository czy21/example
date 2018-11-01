CREATE TABLE `sys_role`  (
  `RoleId` varchar(36)  NOT NULL,
  `RoleName` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `Remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `AddedTime` datetime(0) DEFAULT NULL,
  `ModifiedTime` datetime(0) DEFAULT NULL,
  `AddedUser` varchar(36) DEFAULT NULL,
  `ModifiedUser` varchar(36) DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`RoleId`) 
) COMMENT='角色表';