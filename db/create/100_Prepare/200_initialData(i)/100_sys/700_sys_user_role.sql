set @user_id = (select user_id from sys_user where login_name='admin');
set @role_id = (select role_id from sys_role where role_name='管理员');
insert into sys_user_role(user_role_id,user_id,role_id,added_time,modified_time,added_user,modified_user)
values(UUID(),@user_id,@role_id,NOW(),NOW(),NULL,NULL);