CREATE TABLE `ent_sys_department`  (
  `id` varchar(36)  NOT NULL,
  `parent_id` varchar(36) NULL COMMENT '上级部门',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `company_id` varchar(36) NOT NULL COMMENT '所属公司',
  {{ TrackedColumns }},
  PRIMARY KEY (`id`) ,
  INDEX `fk_Department_Company`(`company_id`),
  CONSTRAINT `fk_Department_Company` 
  FOREIGN KEY (`company_id`) 
  REFERENCES `ent_sys_company` (`id`) 
  ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='部门表';