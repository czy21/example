update sys_user_role ur,temp_target_key tk 
set ur.user_role_id=tk.id
where ur.user_role_id=tk.temp_id and tk.target_table='sys_user_role';

update sys_user_role c,temp_target_key tk 
set c.role_id=tk.id
where c.role_id=tk.temp_id and tk.target_table='sys_role';

update sys_user_role c,temp_target_key tk 
set c.user_id=tk.id
where c.user_id=tk.temp_id and tk.target_table='sys_user';

ALTER TABLE sys_user_role 
MODIFY user_role_id BIGINT NOT NULL,
MODIFY role_id BIGINT NOT NULL,
MODIFY user_id BIGINT NOT NULL;