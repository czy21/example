CREATE TABLE `ent_sys_material` (
  `id` varchar(36) NOT NULL,
  `name` varchar(200) NOT NULL COMMENT '唯一文件名(uuid.*)',
  `kind` varchar(200) NOT NULL COMMENT '文件类型',
  `material_target_key` varchar(100) NOT NULL COMMENT '文件存储目标Key',
  ${{{TrackedColumns}}},
  PRIMARY KEY (`id`),
  INDEX `fk_Material_MaterialTarget`(`material_target_key`),
  CONSTRAINT `fk_Material_MaterialTarget`
  FOREIGN KEY (`material_target_key`)
  REFERENCES `ent_sys_material_target` (`key`)
  ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='公司表';