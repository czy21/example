set @UserId = (select UserId from sys_user where LoginName='admin');
set @RoleId = (select RoleId from sys_role where RoleName='管理员');
insert into sys_user_role(UserRoleId,UserId,RoleId,AddedTime,ModifiedTime,AddedUser,ModifiedUser)
values(UUID(),@UserId,@RoleId,NOW(),NOW(),NULL,NULL);