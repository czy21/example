CREATE TABLE `sys_user`  (
  `user_id` varchar(36)  NOT NULL,
  `login_name` varchar(50)  NOT NULL COMMENT '用户账号',
  `password` varchar(100)  NULL DEFAULT NULL COMMENT '用户密码',
  `user_name` varchar(50)  NOT NULL COMMENT '用户姓名',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '用户电话',
  `is_header` bit(1) NOT NULL DEFAULT 0 COMMENT '是否是部长',
  `department_id` varchar(36)  NOT NULL COMMENT '所属部门',
  `added_time` datetime(0) DEFAULT NULL,
  `modified_time` datetime(0) DEFAULT NULL,
  `added_user` varchar(36) DEFAULT NULL,
  `modified_user` varchar(36) DEFAULT NULL,
  `enabled` bit(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`user_id`) ,
  INDEX `fk_user_department`(`department_id`) ,
  CONSTRAINT `fk_user_department` FOREIGN KEY (`department_id`) REFERENCES `sys_department` (`department_id`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='用户表';