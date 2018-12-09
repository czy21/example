set @department_id = (select department_id from sys_department where department_name='研发部');
insert into sys_user (user_id,login_name,password,user_name,email,phone,is_header,department_id,added_time,modified_time,added_user,modified_user,enabled)
values(UUID(),'admin', 'admin', '管理员', '805899926@qq.com', NULL, 0, @department_id,NOW(),NOW(),NULL,NULL,1);
