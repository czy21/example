set @RoleId=(select RoleId from sys_role where RoleName='管理员');
set @MenuId=(select MenuId from sys_menu where MenuName='系统管理');
insert into sys_role_menu(RoleMenuId,RoleId,MenuId,AddedTime,ModifiedTime,AddedUser,ModifiedUser)
values(UUID(),@RoleId,@MenuId,NOW(),NOW(),NULL,NULL);