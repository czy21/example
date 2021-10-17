CREATE TABLE `ent_sale`  (
  `id` varchar(36)  NOT NULL,
  `order_date` datetime null default NULL COMMENT '订单日期',
  `from_institution_code` varchar(100) NULL DEFAULT NULL COMMENT '经销商代码',
  `from_institution_name` varchar(100) NULL DEFAULT NULL COMMENT '经销商名称',
  `to_institution_code` varchar(100) NULL DEFAULT NULL COMMENT '客户代码',
  `to_institution_name` varchar(100) NULL DEFAULT NULL COMMENT '客户名称',
  `product_code` varchar(100) NULL DEFAULT NULL COMMENT '产品代码',
  `product_name` varchar(100) NULL DEFAULT NULL COMMENT '产品名称',
  `product_spec` varchar(100) NULL DEFAULT NULL COMMENT '产品规格',
  `product_unit` varchar(100) NULL DEFAULT NULL COMMENT '单位',
  `product_quantity` decimal(19,6) NULL DEFAULT NULL COMMENT '数量',
  `product_batch_number` varchar(100) NULL DEFAULT NULL COMMENT '产品批号',
  `product_price` decimal(19,6) NULL DEFAULT NULL COMMENT '单价',
  ${{{TrackedColumns}}},
  PRIMARY KEY (`id`)
) COMMENT='库存';