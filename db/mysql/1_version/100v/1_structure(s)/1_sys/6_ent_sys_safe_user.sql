CREATE TABLE `ent_sys_safe_user`  (
  `id` varchar(36)  NOT NULL,
  `name` varchar(50)  NOT NULL COMMENT '姓名',
  `phone_no` varchar(50)  NOT NULL COMMENT '手机号',
  `id_num` varchar(50)  NOT NULL COMMENT '身份证号',
  {{ TrackColumn }},
  PRIMARY KEY (`id`)
) COMMENT='用户脱敏';