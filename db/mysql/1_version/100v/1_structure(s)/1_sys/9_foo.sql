CREATE TABLE `foo`  (
  `id` varchar(36)  NOT NULL,
  `number` varchar(100)  NOT NULL,
  {{ TrackColumn }},
  PRIMARY KEY (`id`)
) COMMENT='订单表';