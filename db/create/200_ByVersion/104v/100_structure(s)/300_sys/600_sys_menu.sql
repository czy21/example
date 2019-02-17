update sys_menu m,temp_target_key tk 
set m.menu_id=tk.id
where m.menu_id=tk.temp_id and tk.target_table='sys_menu';

update sys_menu m,temp_target_key tk 
set m.parent_id=tk.id
where m.parent_id=tk.temp_id and tk.target_table='sys_menu';

update sys_menu m 
set m.parent_id='0' 
where m.parent_id='00000000-0000-0000-0000-000000000000';

ALTER TABLE sys_menu 
MODIFY menu_id BIGINT NOT NULL,
MODIFY parent_id BIGINT NOT NULL;