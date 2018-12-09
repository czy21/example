CREATE TABLE `sys_department`  (
  `department_id` varchar(36)  NOT NULL,
  `parent_id` varchar(36) NOT NULL COMMENT '上级部门',
  `department_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `phone` varchar(20) DEFAULT NULL COMMENT '部门电话',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `company_id` varchar(36) NOT NULL COMMENT '所属公司',
  `added_time` datetime(0) DEFAULT NULL,
  `modified_time` datetime(0) DEFAULT NULL,
  `added_user` varchar(36) DEFAULT NULL,
  `modified_user` varchar(36) DEFAULT NULL,
  `enabled` bit(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`Department_id`) ,
  INDEX `fk_department_company`(`company_id`) ,
  CONSTRAINT `fk_department_company` FOREIGN KEY (`company_id`) REFERENCES `sys_company` (`company_id`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='部门表';