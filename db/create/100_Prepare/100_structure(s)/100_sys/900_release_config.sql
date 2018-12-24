CREATE TABLE `release_config`  (
  `config_key` varchar(20)  NOT NULL,
  `config_value` longtext  NULL,
  `remark` text  NULL,
  PRIMARY KEY (`config_key`)
) COMMENT='版本配置表';

INSERT INTO `release_config`(`config_key`, `config_value`, `remark`) VALUES ('BuildDate', NOW(), NULL);
INSERT INTO `release_config`(`config_key`, `config_value`, `remark`) VALUES ('Version', '1000', NULL);
