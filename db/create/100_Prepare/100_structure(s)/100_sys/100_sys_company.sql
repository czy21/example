CREATE TABLE `sys_company`  (
  `CompanyId` varchar(36) NOT NULL,
  `CompanyName` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `ContactPerson` varchar(20) DEFAULT NULL COMMENT '联系人',
  `Location` varchar(100) DEFAULT NULL COMMENT '公司地点',
  `Phone` varchar(20) DEFAULT NULL COMMENT '公司电话',
  `Fax` varchar(20) DEFAULT NULL COMMENT '公司传真',
  `Postcode` varchar(20) DEFAULT NULL COMMENT '公司邮编',
  `Remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `AddedTime` datetime(0) DEFAULT NULL,
  `ModifiedTime` datetime(0) DEFAULT NULL,
  `AddedUser` varchar(36) DEFAULT NULL,
  `ModifiedUser` varchar(36) DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`CompanyId`) 
) COMMENT='公司表';