ALTER TABLE `worker_node` 
CHANGE COLUMN `ID` `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'auto increment id' FIRST,
CHANGE COLUMN `HOST_NAME` `host_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'host name' AFTER `id`,
CHANGE COLUMN `PORT` `port` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'port' AFTER `host_name`,
CHANGE COLUMN `TYPE` `type` int(11) NOT NULL COMMENT 'node type: ACTUAL or CONTAINER' AFTER `port`,
CHANGE COLUMN `LAUNCH_DATE` `launch_date` date NOT NULL COMMENT 'launch date' AFTER `type`,
CHANGE COLUMN `MODIFIED` `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT 'modified time' AFTER `launch_date`,
CHANGE COLUMN `CREATED` `added_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT 'created time' AFTER `modified_time`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`id`);
alter table `worker_node` add sequence_value BIGINT null comment 'initial sequence';