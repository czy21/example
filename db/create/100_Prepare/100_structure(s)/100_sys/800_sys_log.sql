CREATE TABLE `sys_log`  (
  `log_id` varchar(36)  NOT NULL,
  `description` varchar(50) DEFAULT NULL COMMENT '日志信息描述',
  `method` varchar(255) DEFAULT NULL COMMENT '方法名称',
  `log_type` bit(1) NOT NULL DEFAULT 0 COMMENT '日志类型 0是正常，1是异常',
  `request_ip` varchar(30) DEFAULT NULL COMMENT '请求的ip',
  `exception_code` varchar(50) DEFAULT NULL COMMENT '异常错误码',
  `exception_detail` varchar(255) DEFAULT NULL COMMENT '异常详情',
  `user_id` varchar(36) DEFAULT NULL COMMENT '请求的用户id',
  `added_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) COMMENT='系统日志表';