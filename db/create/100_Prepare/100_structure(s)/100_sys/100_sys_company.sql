CREATE TABLE `oa_company`  (
  `CompanyId` varchar(36) NOT NULL,
  `CompanyName` varchar(50) DEFAULT NULL,
  `ContactPerson` varchar(20) DEFAULT NULL,
  `Location` varchar(100) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Fax` varchar(20) DEFAULT NULL,
  `Postcode` varchar(20) DEFAULT NULL,
  `Comment` varchar(100) DEFAULT NULL,
  `AddedTime` datetime(0) DEFAULT NULL,
  `ModifiedTime` datetime(0) DEFAULT NULL,
  `AddedUser` varchar(36) DEFAULT NULL,
  `ModifiedUser` varchar(36) DEFAULT NULL,
  `Enabled` bit(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`CompanyId`) 
) COMMENT='公司表';