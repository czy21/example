CREATE TABLE `ent_book`  (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '名称',
  {{ TrackColumn }},
  PRIMARY KEY (`id`)
) COMMENT='书表';