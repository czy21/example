CREATE TABLE `ent_stock`  (
  `id` varchar(36)  NOT NULL,
  `name` varchar(50)  NOT NULL COMMENT '名称',
  `count` bigint  NULL DEFAULT NULL COMMENT '库存',
  {{ TrackColumn }},
  PRIMARY KEY (`id`)
) COMMENT='库存';