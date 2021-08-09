CREATE TABLE `ent_migrate_performance_log`  (
  `id` varchar(36)  NOT NULL,
  `target` varchar(100) NULL,
  `table_count` int null,
  `batch_count` int null,
  `duration_unit` varchar(100) null,
  `duration`  decimal(19,9) null,
  `sequence` int null,
  `batch_id` varchar(36) null,
`created_date` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                          `created_user` varchar(36) DEFAULT NULL,
                          `modified_date` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                          `modified_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) COMMENT='迁移性能日志';

--   ${{{TrackedColumns}}},