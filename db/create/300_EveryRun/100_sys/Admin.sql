
set @max_role_function_id = max_target_table_id{{role_function_id,sys_role_function}};

INSERT INTO sys_role_function(`role_function_id`,`role_id`, `function_id`, `added_time`, `modified_time`, `added_user`, `modified_user`)
select (@max_role_function_id:=@max_role_function_id + 1) AS role_function_id,r.role_id,f.function_id,now(),now(),null,null from sys_role r
cross join sys_function f 
left join sys_role_function rf on rf.role_id = r.role_id and rf.function_id = f.function_id
where r.role_name='管理员' and rf.role_function_id is null;

set @max_role_menu_id = max_target_table_id{{role_menu_id,sys_role_menu}};

INSERT INTO sys_role_menu(`role_menu_id`,`role_id`, `menu_id`, `added_time`, `modified_time`, `added_user`, `modified_user`)
select (@max_role_menu_id:=@max_role_menu_id + 1) AS role_menu_id,r.role_id,m.menu_id,now(),now(),null,null from sys_role r
cross join sys_menu m 
left join sys_role_menu rm on rm.role_id = r.role_id and rm.menu_id = m.menu_id
where r.role_name='管理员' and rm.role_menu_id is null;