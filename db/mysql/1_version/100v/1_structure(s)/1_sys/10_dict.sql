CREATE TABLE `sys_dict` (
    `id`       varchar(36)  NOT NULL,
    `label`    varchar(100) NOT NULL COMMENT 'label',
    `value`    varchar(100) NOT NULL COMMENT 'value',
    `type`     varchar(100) NOT NULL COMMENT 'value类型;0:String,1:Int',
    `sort`     int          NOT NULL DEFAULT 0 COMMENT '排序',
    `category` tinyint(1)   NOT NULL DEFAULT 0 COMMENT '类别;0:系统字典,1:业务字典'
    `parent_id` varchar (36) NULL COMMENT '上级',
    {{ TrackColumn }},
    PRIMARY KEY (`id`)
) COMMENT='公司表';