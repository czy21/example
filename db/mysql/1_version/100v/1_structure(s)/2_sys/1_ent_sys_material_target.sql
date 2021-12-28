CREATE TABLE `ent_sys_material_target` (
  `key` varchar(100) NOT NULL,
  `root_url` varchar(255) NOT NULL COMMENT '外部地址 需要带完整访问地址',
  `root_path` varchar(255) NOT NULL COMMENT '内部地址 需要带相对地址',
  `target_kind` varchar(100) NOT NULL COMMENT '存储目标类型(OSS对象存储，LOCAL本地存储)',
  {{ TrackedColumns }},
  PRIMARY KEY (`key`)
) COMMENT='文件存储目标表';