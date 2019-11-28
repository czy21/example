CREATE TABLE `ent_sys_user_role`  (
  `id` varchar(36)  NOT NULL,
  `user_id` varchar(36)  NOT NULL,
  `role_id` varchar(36)  NOT NULL,
  ${{{TrackedColumns}}},
  PRIMARY KEY (`id`) ,
  INDEX `fk_UserRole_Role`(`role_id`) ,
  INDEX `fk_UserRole_User`(`user_id`) ,
  CONSTRAINT `fk_UserRole_Role` FOREIGN KEY (`role_id`) REFERENCES `ent_sys_role` (`id`) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT `fk_UserRole_User` FOREIGN KEY (`user_id`) REFERENCES `ent_sys_user` (`id`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='用户角色表';