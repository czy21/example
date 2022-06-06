CREATE TABLE `ent_sys_role`  (
  `id` varchar(36)  NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  {{ TrackColumn }},
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) COMMENT='角色表';