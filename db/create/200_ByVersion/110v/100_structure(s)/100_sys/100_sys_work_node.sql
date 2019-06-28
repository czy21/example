CREATE TABLE `sys_work_node`
(
`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
`launch_date` datetime(0) NOT NULL,
`sequence_value` BIGINT NOT NULL,
`added_time` datetime(0) DEFAULT NULL,
`modified_time` datetime(0) DEFAULT NULL,
PRIMARY KEY(id)
) COMMENT='DB WorkerID Assigner for UID Generator';