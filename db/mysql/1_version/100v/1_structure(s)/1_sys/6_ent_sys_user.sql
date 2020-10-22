CREATE TABLE `ent_sys_user`  (
  `id` varchar(36)  NOT NULL,
  `account` varchar(50)  NOT NULL COMMENT '用户账号',
  `password` varchar(100)  NULL DEFAULT NULL COMMENT '用户密码',
  `user_name` varchar(50)  NOT NULL COMMENT '用户姓名',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `department_id` varchar(36)  NOT NULL COMMENT '所属部门',
  ${{{TrackedColumns}}},
  PRIMARY KEY (`id`) ,
  INDEX `fk_User_Department`(`department_id`) ,
  CONSTRAINT `fk_User_Department` FOREIGN KEY (`department_id`) 
  REFERENCES `ent_sys_department` (`id`) 
  ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='用户表';