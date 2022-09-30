CREATE TABLE `ent_book`  (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '名称',
  {{ TrackColumn }},
  PRIMARY KEY (`id`)
) COMMENT='书表';



CREATE TABLE `kafka_topic` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `value` int DEFAULT NULL COMMENT '指',
  create_time TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
	create_user varchar(36)  NULL COMMENT '创建人',
	update_time TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
	update_user varchar(36)  NULL COMMENT '更新人',
	deleted bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) COMMENT='kafka-topic';