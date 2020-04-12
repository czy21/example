CREATE TABLE `ent_mdm_order`  (
  `id` varchar(36)  NOT NULL,
  `state` varchar(100)  NOT NULL,
  ${{{TrackedColumns}}},
  PRIMARY KEY (`id`)
) COMMENT='订单表';