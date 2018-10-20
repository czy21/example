DROP TABLE IF EXISTS `oa_department`;
CREATE TABLE `oa_department`  (
  `DepartmentId` varchar(36)  NOT NULL,
  `AddedTime` datetime(0) NULL DEFAULT NULL,
  `ModifiedTime` datetime(0) NULL DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT b'1',
  `Name` varchar(50)  NULL DEFAULT NULL,
  `ParentId` varchar(36)  NOT NULL,
  `Phone` varchar(20)  NULL DEFAULT NULL,
  `Comment` varchar(100)  NULL DEFAULT NULL,
  `CompanyId` varchar(36)  NOT NULL,
  PRIMARY KEY (`DepartmentId`) ,
  INDEX `fk_Department_Company`(`CompanyId`) ,
  CONSTRAINT `fk_Department_Company` FOREIGN KEY (`CompanyId`) REFERENCES `oa_company` (`CompanyId`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='部门表';