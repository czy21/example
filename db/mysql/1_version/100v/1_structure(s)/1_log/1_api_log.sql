CREATE TABLE `api_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `args` LONGTEXT DEFAULT NULL,
  {{ CreateTimeColumn }},
  PRIMARY KEY (`id`)
) COMMENT='请求日志';