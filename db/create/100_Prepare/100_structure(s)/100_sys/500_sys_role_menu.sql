CREATE TABLE `sys_role_menu`  (
  `role_menu_id` varchar(36)  NOT NULL,
  `role_id` varchar(36)  NOT NULL,
  `menu_id` varchar(36)  NOT NULL,
  `added_time` datetime(0) DEFAULT NULL,
  `modified_time` datetime(0) DEFAULT NULL,
  `added_user` varchar(36) DEFAULT NULL,
  `modified_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`role_menu_id`) ,
  INDEX `fk_role_menu_menu`(`menu_id`) ,
  INDEX `fk_role_menu_role`(`role_id`) ,
  CONSTRAINT `fk_role_menu_menu` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT `fk_role_menu_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='角色菜单表';