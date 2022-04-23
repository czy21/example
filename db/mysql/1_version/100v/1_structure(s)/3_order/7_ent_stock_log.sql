CREATE TABLE `ent_stock_log`  (
  `id` varchar(36)  NOT NULL,
  `stock_id` varchar(36)  NULL DEFAULT NULL COMMENT '库存Id',
  `user_id` varchar(36)  NOT NULL COMMENT '用户Id',
  {{ TrackColumn }},
  PRIMARY KEY (`id`)
) COMMENT='库存日志';