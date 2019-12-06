CREATE TABLE `met_sys_permission` (
  `key` varchar(100)  NOT NULL,
  `name` varchar(50) NOT NULL,
  `sort` int(11) DEFAULT 0,
  PRIMARY KEY (`key`)
) COMMENT='权限表';