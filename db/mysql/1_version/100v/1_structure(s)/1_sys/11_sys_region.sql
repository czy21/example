CREATE TABLE `sys_region` (
  `id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `parent_id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父id',
  `level` int DEFAULT NULL COMMENT '地区级别 1-省 2-市 3-区',
  PRIMARY KEY (`id`)
) COMMENT='地区表';