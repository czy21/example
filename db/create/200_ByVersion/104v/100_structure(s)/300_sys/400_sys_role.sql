update sys_role c,temp_target_key tk 
set c.role_id=tk.id
where c.role_id=tk.temp_id and tk.target_table='sys_role';

ALTER TABLE sys_role MODIFY role_id BIGINT NOT NULL;