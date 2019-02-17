update sys_company c,temp_target_key tk
set c.company_id=tk.id
where c.company_id=tk.temp_id and tk.target_table='sys_company';
ALTER TABLE sys_company MODIFY company_id BIGINT NOT NULL;