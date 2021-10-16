CREATE TABLE `ent_migrate_performance_log`  (
  `id` varchar(36)  NOT NULL,
  `target` varchar(100) NULL,
  `table_count` int null,
  `batch_count` int null,
  `duration_unit` varchar(100) null,
  `duration`  decimal(19,9) null,
  `sequence` int null,
  `batch_id` varchar(36) null,
  ${{{TrackedColumns}}},
  PRIMARY KEY (`id`)
) COMMENT='迁移性能日志';