CREATE TABLE `oa_department`  (
  `DepartmentId` varchar(36)  NOT NULL,
  `ParentId` varchar(36) NOT NULL,
  `DepartmentName` varchar(50) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Comment` varchar(100) DEFAULT NULL,
  `CompanyId` varchar(36) NOT NULL,
  `AddedTime` datetime(0) DEFAULT NULL,
  `ModifiedTime` datetime(0) DEFAULT NULL,
  `AddedUser` varchar(36) DEFAULT NULL,
  `ModifiedUser` varchar(36) DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`DepartmentId`) ,
  INDEX `fk_Department_Company`(`CompanyId`) ,
  CONSTRAINT `fk_Department_Company` FOREIGN KEY (`CompanyId`) REFERENCES `oa_company` (`CompanyId`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='部门表';