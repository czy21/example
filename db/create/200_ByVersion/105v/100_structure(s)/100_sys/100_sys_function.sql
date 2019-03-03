CREATE TABLE `sys_function`  (
  `function_id` bigint(20) NOT NULL,
	`function_code` varchar(50) NOT NULL COMMENT '权限值',
	`function_name` varchar(100) DEFAULT NULL COMMENT '权限名称',
  `sort`          int(11) DEFAULT NULL COMMENT '排序',
  `remark`        varchar(100) DEFAULT NULL COMMENT '备注',
  `added_time` datetime(0) DEFAULT NULL,
  `modified_time` datetime(0) DEFAULT NULL,
  `added_user` varchar(36) DEFAULT NULL,
  `modified_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`function_id`)
) COMMENT='权限表';