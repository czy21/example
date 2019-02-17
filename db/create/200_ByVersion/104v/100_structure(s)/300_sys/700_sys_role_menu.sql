update sys_role_menu rm,temp_target_key tk 
set rm.role_menu_id=tk.id
where rm.role_menu_id=tk.temp_id and tk.target_table='sys_role_menu';

update sys_role_menu c,temp_target_key tk 
set c.role_id=tk.id
where c.role_id=tk.temp_id and tk.target_table='sys_role';

update sys_role_menu c,temp_target_key tk 
set c.menu_id=tk.id
where c.menu_id=tk.temp_id and tk.target_table='sys_menu';


ALTER TABLE sys_role_menu 
MODIFY role_menu_id BIGINT NOT NULL,
MODIFY role_id BIGINT NOT NULL,
MODIFY menu_id BIGINT NOT NULL;