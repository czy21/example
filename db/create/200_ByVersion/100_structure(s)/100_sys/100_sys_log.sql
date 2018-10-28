CREATE TABLE `oa_log`  (
  `LogId` varchar(36)  NOT NULL,
  `Description` varchar(50) DEFAULT NULL COMMENT '日志信息描述',
  `Method` varchar(255) DEFAULT NULL COMMENT '方法名称',
  `LogType` bit(1) NOT NULL DEFAULT 0 COMMENT '日志类型 0是正常，1是异常',
  `RequestIp` varchar(30) DEFAULT NULL COMMENT '请求的ip',
  `ExceptionCode` varchar(50) DEFAULT NULL COMMENT '异常错误码',
  `ExceptionDetail` varchar(255) DEFAULT NULL COMMENT '异常详情',
  `UserId` varchar(36) DEFAULT NULL COMMENT '请求的用户id',
  `AddedTime` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`LogId`) 
) COMMENT='系统日志表';