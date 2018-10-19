DROP TABLE IF EXISTS `oa_role`;
CREATE TABLE `oa_role`  (
  `RoleId` varchar(36)  NOT NULL,
  `AddedTime` datetime(0) NULL DEFAULT NULL,
  `ModifiedTime` datetime(0) NULL DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT b'1',
  `Name` varchar(50)  NULL DEFAULT NULL,
  `Comment` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`RoleId`) 
) COMMENT='角色表';