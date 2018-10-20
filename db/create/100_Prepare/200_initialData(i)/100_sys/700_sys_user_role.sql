set @UserId = (select UserId from oa_user where LoginName='admin');
set @RoleId = (select RoleId from oa_role where RoleName='管理员');
insert into oa_user_role(UserRoleId,UserId,RoleId,AddedTime,ModifiedTime,AddedUser,ModifiedUser)
values(UUID(),@UserId,@RoleId,NOW(),NOW(),NULL,NULL);