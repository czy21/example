set @max_menu_id = max_target_table_id{{menu_id,sys_menu}};

INSERT INTO sys_menu(`menu_id`, `parent_id`, `menu_name`, `icon`, `sort`, `url`, `remark`, `added_time`, `modified_time`, `added_user`, `modified_user`) VALUES (@max_menu_id+1, 2959224461124968494, '权限管理', 'erp-icon-authorization', 7, '/system/function', NULL, now(),now(), NULL, NULL);