CREATE TABLE `sys_role_function`  (
  `role_function_id` bigint(20)  NOT NULL,
  `role_id` bigint(20)  NOT NULL,
  `function_id` bigint(20)  NOT NULL,
  `added_time` datetime(0) DEFAULT NULL,
  `modified_time` datetime(0) DEFAULT NULL,
  `added_user` varchar(36) DEFAULT NULL,
  `modified_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`role_function_id`) ,
  INDEX `fk_role_function_function`(`function_id`) ,
  INDEX `fk_role_function_role`(`role_id`) ,
  CONSTRAINT `fk_role_function_function` FOREIGN KEY (`function_id`) REFERENCES `sys_function` (`function_id`) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT `fk_role_function_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='角色权限表';