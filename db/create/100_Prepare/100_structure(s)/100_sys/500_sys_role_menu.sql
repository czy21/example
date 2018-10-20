DROP TABLE IF EXISTS `oa_role_menu`;
CREATE TABLE `oa_role_menu`  (
  `RoleMenuId` varchar(36)  NOT NULL,
  `AddedTime` datetime(0) NULL DEFAULT NULL,
  `ModifiedTime` datetime(0) NULL DEFAULT NULL,
  `RoleId` varchar(36)  NOT NULL,
  `MenuId` varchar(36)  NOT NULL,
  PRIMARY KEY (`RoleMenuId`) ,
  INDEX `fk_RoleMenu_Menu`(`MenuId`) ,
  INDEX `fk_RoleMenu_Role`(`RoleId`) ,
  CONSTRAINT `fk_RoleMenu_Menu` FOREIGN KEY (`MenuId`) REFERENCES `oa_menu` (`MenuId`) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT `fk_RoleMenu_Role` FOREIGN KEY (`RoleId`) REFERENCES `oa_role` (`RoleId`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='角色菜单表';