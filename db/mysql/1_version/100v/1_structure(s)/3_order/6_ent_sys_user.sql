CREATE TABLE `ent_stock`  (
  `id` varchar(36)  NOT NULL,
  `name` varchar(50)  NOT NULL COMMENT '名称',
  `count` bigint  NULL DEFAULT NULL COMMENT '库存',
  ${{{TrackedColumns}}},
  PRIMARY KEY (`id`)
) COMMENT='用户表';