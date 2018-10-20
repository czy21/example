set @CompanyId=(select CompanyId from oa_company where CompanyName='Demo公司');
insert into oa_department (DepartmentId,DepartmentName,ParentId,Phone,Comment,CompanyId,AddedTime,ModifiedTime,AddedUser,ModifiedUser,Enabled)
 VALUES (UUID(),'研发部','00000000-0000-0000-0000-000000000000','15145033859',NULL,@CompanyId,NOW(),NOW(),NULL,NULL,1);
