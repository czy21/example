CREATE TABLE `oa_menu`  (
  `MenuId` varchar(36)  NOT NULL,
  `ParentId` varchar(36)  NOT NULL,
  `MenuName` varchar(50) DEFAULT NULL,
  `Icon` varchar(100) DEFAULT NULL,
  `Sort` int(11) DEFAULT NULL,
  `Url` varchar(50) DEFAULT NULL,
  `IsMenu` bit(1) NOT NULL DEFAULT 0,
  `Comment` varchar(100) DEFAULT NULL,
  `AddedTime` datetime(0) DEFAULT NULL,
  `ModifiedTime` datetime(0) DEFAULT NULL,
  `AddedUser` varchar(36) DEFAULT NULL,
  `ModifiedUser` varchar(36) DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`MenuId`) 
) COMMENT='菜单权限表';