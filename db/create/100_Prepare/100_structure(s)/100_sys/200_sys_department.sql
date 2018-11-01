CREATE TABLE `sys_department`  (
  `DepartmentId` varchar(36)  NOT NULL,
  `ParentId` varchar(36) NOT NULL COMMENT '上级部门',
  `DepartmentName` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `Phone` varchar(20) DEFAULT NULL COMMENT '部门电话',
  `Remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `CompanyId` varchar(36) NOT NULL COMMENT '所属公司',
  `AddedTime` datetime(0) DEFAULT NULL,
  `ModifiedTime` datetime(0) DEFAULT NULL,
  `AddedUser` varchar(36) DEFAULT NULL,
  `ModifiedUser` varchar(36) DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`DepartmentId`) ,
  INDEX `fk_Department_Company`(`CompanyId`) ,
  CONSTRAINT `fk_Department_Company` FOREIGN KEY (`CompanyId`) REFERENCES `sys_company` (`CompanyId`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='部门表';