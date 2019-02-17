update sys_department c,temp_target_key tk 
set c.department_id=tk.id
where c.department_id=tk.temp_id and tk.target_table='sys_department';

update sys_department c,temp_target_key tk 
set c.company_id=tk.id
where c.company_id=tk.temp_id and tk.target_table='sys_company';

update sys_department d,temp_target_key tk 
set d.parent_id=tk.id
where d.parent_id=tk.temp_id and tk.target_table='sys_department';

update sys_department m 
set m.parent_id='0' 
where m.parent_id='00000000-0000-0000-0000-000000000000';

ALTER TABLE sys_department 
MODIFY department_id BIGINT NOT NULL,
MODIFY parent_id BIGINT NOT NULL,
MODIFY company_id BIGINT NOT NULL;