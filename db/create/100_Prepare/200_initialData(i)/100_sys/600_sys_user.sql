set @DepartmentId = (select DepartmentId from sys_department where DepartmentName='研发部');
insert into sys_user (UserId,LoginName,Password,UserName,Email,Phone,IsHeader,DepartmentId,AddedTime,ModifiedTime,AddedUser,ModifiedUser,Enabled)
values(UUID(),'admin', 'admin', '管理员', '805899926@qq.com', NULL, 0, @DepartmentId,NOW(),NOW(),NULL,NULL,1);
