set @max_function_id = max_target_table_id{{function_id,sys_function}};

INSERT INTO sys_function (`function_id`, `function_code`, `function_name`, `sort`, `added_time`, `modified_time`, `added_user`, `modified_user`, `remark`)
values 
(@max_function_id:=@max_function_id+1, 'SearchUser', '查询用户', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'AddUser', '添加用户', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'EditUser', '修改用户', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'DisableUser', '禁用用户', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'AllotUserRole', '分配用户角色', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'SearchRole', '查询角色', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'AddRole', '添加角色', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'EditRole', '修改角色', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'AllotRoleMenu', '分配角色菜单', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'SearchLog', '查询日志', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'SearchDepartment', '查询部门', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'AddDepartment', '添加部门', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'EditDepartment', '修改部门', NULL, now(), now(), NULL, NULL, NULL),
(@max_function_id:=@max_function_id+1, 'DisableDepartment', '禁用部门', NULL, now(), now(), NULL, NULL, NULL);