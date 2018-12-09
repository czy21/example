set @role_id=(select role_id from sys_role where role_name='管理员');
set @menu_id=(select menu_id from sys_menu where menu_name='系统管理');
insert into sys_role_menu(role_menu_id,role_id,menu_id,added_time,modified_time,added_user,modified_user)
values(UUID(),@role_id,@menu_id,NOW(),NOW(),NULL,NULL);