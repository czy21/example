DROP TABLE IF EXISTS `oa_company`;
CREATE TABLE `oa_company`  (
  `CompanyId` varchar(36)  NOT NULL,
  `AddedTime` datetime(0) NULL DEFAULT NULL,
  `ModifiedTime` datetime(0) NULL DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT b'1',
  `Name` varchar(50)  NULL DEFAULT NULL,
  `ContactPerson` varchar(20)  NULL DEFAULT NULL,
  `Location` varchar(100)  NULL DEFAULT NULL,
  `Phone` varchar(20)  NULL DEFAULT NULL,
  `Fax` varchar(20)  NULL DEFAULT NULL,
  `Postcode` varchar(20)  NULL DEFAULT NULL,
  `Comment` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`CompanyId`) 
) COMMENT='公司表';