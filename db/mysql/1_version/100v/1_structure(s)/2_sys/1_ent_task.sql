CREATE TABLE `ent_task`  (
  `id` varchar(36)  NOT NULL,
  `code` varchar(100) not null,
  `status` varchar(100) not null,
  `execute_count` int not null,
  `batch_id` varchar(36) null,
`created_date` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                          `created_user` varchar(36) DEFAULT NULL,
                          `modified_date` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                          `modified_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) COMMENT='任务';

CREATE TABLE `ent_task_log`  (
  `id` varchar(36)  NOT NULL,
  `code` varchar(100) not null,
  `batch_id` varchar(36) null,
`created_date` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                          `created_user` varchar(36) DEFAULT NULL,
                          `modified_date` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                          `modified_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) COMMENT='任务';

--   ${{{TrackedColumns}}},