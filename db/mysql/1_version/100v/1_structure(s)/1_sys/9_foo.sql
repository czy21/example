CREATE TABLE `foo`  (
  `id` varchar(36)  NOT NULL,
  `number` varchar(100)  NOT NULL,
  ${{{TrackedColumns}}},
  PRIMARY KEY (`id`)
) COMMENT='订单表';