CREATE TABLE `sys_menu`  (
  `menu_id` varchar(36)  NOT NULL,
  `parent_id` varchar(36)  NOT NULL COMMENT '上级菜单',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `url` varchar(50) DEFAULT NULL COMMENT '菜单或权限地址',
  `is_menu` bit(1) NOT NULL DEFAULT 0 COMMENT '是否是菜单 0-权限,1-菜单',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `added_time` datetime(0) DEFAULT NULL,
  `modified_time` datetime(0) DEFAULT NULL,
  `added_user` varchar(36) DEFAULT NULL,
  `modified_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) COMMENT='菜单权限表';