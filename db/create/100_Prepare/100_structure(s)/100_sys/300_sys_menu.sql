CREATE TABLE `sys_menu`  (
  `MenuId` varchar(36)  NOT NULL,
  `ParentId` varchar(36)  NOT NULL COMMENT '上级菜单',
  `MenuName` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `Icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `Sort` int(11) DEFAULT NULL COMMENT '排序',
  `Url` varchar(50) DEFAULT NULL COMMENT '菜单或权限地址',
  `IsMenu` bit(1) NOT NULL DEFAULT 0 COMMENT '是否是菜单 0-权限,1-菜单',
  `Remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `AddedTime` datetime(0) DEFAULT NULL,
  `ModifiedTime` datetime(0) DEFAULT NULL,
  `AddedUser` varchar(36) DEFAULT NULL,
  `ModifiedUser` varchar(36) DEFAULT NULL
  PRIMARY KEY (`MenuId`) 
) COMMENT='菜单权限表';