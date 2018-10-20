DROP TABLE IF EXISTS `oa_menu`;
CREATE TABLE `oa_menu`  (
  `MenuId` varchar(36)  NOT NULL,
  `AddedTime` datetime(0) NULL DEFAULT NULL,
  `ModifiedTime` datetime(0) NULL DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT b'1',
  `ParentId` varchar(36)  NOT NULL,
  `Name` varchar(50)  NULL DEFAULT NULL,
  `Icon` varchar(100)  NULL DEFAULT NULL,
  `Sort` int(11) NOT NULL DEFAULT 0,
  `Url` varchar(50)  NULL DEFAULT NULL,
  `IsMenu` bit(1) NOT NULL DEFAULT b'0',
  `Comment` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`MenuId`) 
) COMMENT='菜单权限表';