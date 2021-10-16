CREATE TABLE `ent_task`  (
  `id` varchar(36)  NOT NULL,
  `code` varchar(100) not null,
  `status` varchar(100) not null,
  `execute_count` int not null,
  `batch_id` varchar(36) null,
  ${{{TrackedColumns}}},
  PRIMARY KEY (`id`)
) COMMENT='任务';

CREATE TABLE `ent_task_log`  (
  `id` varchar(36)  NOT NULL,
  `code` varchar(100) not null,
  `batch_id` varchar(36) null,
  ${{{TrackedColumns}}},
  PRIMARY KEY (`id`)
) COMMENT='任务';