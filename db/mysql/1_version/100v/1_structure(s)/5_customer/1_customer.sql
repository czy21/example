CREATE TABLE `customer`  (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `gender` TINYINT(1) NULL COMMENT '性别;(0:男,1:女)',
  `id_num` varchar(50) NULL COMMENT '身份证',
  `phone_no` varchar(50) NULL COMMENT '手机号',
  {{ TrackColumn }},
  PRIMARY KEY (`id`)
) COMMENT='客户';