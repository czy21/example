CREATE TABLE `sys_user_role`  (
  `user_role_id` varchar(36)  NOT NULL,
  `user_id` varchar(36)  NOT NULL,
  `role_id` varchar(36)  NOT NULL,
  `added_time` datetime(0) DEFAULT NULL,
  `modified_time` datetime(0) DEFAULT NULL,
  `added_user` varchar(36) DEFAULT NULL,
  `modified_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`) ,
  INDEX `fk_user_role_role`(`role_id`) ,
  INDEX `fk_user_role_user`(`user_id`) ,
  CONSTRAINT `fk_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='用户角色表';