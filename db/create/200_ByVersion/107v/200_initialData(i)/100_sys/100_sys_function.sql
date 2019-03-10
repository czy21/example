set @max_function_id = max_target_table_id{{function_id,sys_function}};

INSERT INTO sys_function (`function_id`, `function_code`, `function_name`, `sort`, `added_time`, `modified_time`, `added_user`, `modified_user`, `remark`)
values 
(@max_function_id:=@max_function_id+1, 'SearchMenu', '查询菜单', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'AddMenu', '添加菜单', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'EditMenu', '修改菜单', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'SearchFunc', '查询权限', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'AddFunc', '添加权限', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'EditFunc', '修改权限', NULL, now(), now(), NULL, NULL, NULL);