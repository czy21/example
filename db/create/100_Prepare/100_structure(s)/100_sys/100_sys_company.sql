CREATE TABLE `sys_company`  (
  `company_id` varchar(36) NOT NULL,
  `company_name` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `contact_person` varchar(20) DEFAULT NULL COMMENT '联系人',
  `location` varchar(100) DEFAULT NULL COMMENT '公司地点',
  `phone` varchar(20) DEFAULT NULL COMMENT '公司电话',
  `fax` varchar(20) DEFAULT NULL COMMENT '公司传真',
  `postcode` varchar(20) DEFAULT NULL COMMENT '公司邮编',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `added_time` datetime(0) DEFAULT NULL,
  `modified_time` datetime(0) DEFAULT NULL,
  `added_user` varchar(36) DEFAULT NULL,
  `modified_user` varchar(36) DEFAULT NULL,
  `enabled` bit(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`company_id`)
) COMMENT='公司表';