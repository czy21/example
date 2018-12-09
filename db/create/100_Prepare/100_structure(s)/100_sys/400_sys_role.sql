CREATE TABLE `sys_role`  (
  `role_id` varchar(36)  NOT NULL,
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `added_time` datetime(0) DEFAULT NULL,
  `modified_time` datetime(0) DEFAULT NULL,
  `added_user` varchar(36) DEFAULT NULL,
  `modified_user` varchar(36) DEFAULT NULL,
  `enabled` bit(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`role_id`)
) COMMENT='角色表';