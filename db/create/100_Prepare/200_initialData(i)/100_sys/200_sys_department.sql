set @CompanyId=(select CompanyId from sys_company where CompanyName='Demo公司');
insert into sys_department (DepartmentId,DepartmentName,ParentId,Phone,Remark,CompanyId,AddedTime,ModifiedTime,AddedUser,ModifiedUser,Enabled)
 VALUES (UUID(),'研发部','00000000-0000-0000-0000-000000000000','15145033859',NULL,@CompanyId,NOW(),NOW(),NULL,NULL,1);
