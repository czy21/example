update sys_user c,temp_target_key tk 
set c.user_id=tk.id
where c.user_id=tk.temp_id and tk.target_table='sys_user';

update sys_user c,temp_target_key tk 
set c.department_id=tk.id
where c.department_id=tk.temp_id and tk.target_table='sys_department';

ALTER TABLE sys_user 
MODIFY department_id BIGINT NOT NULL,
MODIFY user_id BIGINT NOT NULL;