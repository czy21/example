set @company_id=(select company_id from sys_company where company_name='Demo公司');
insert into sys_department (department_id,department_name,parent_id,phone,remark,company_id,added_time,modified_time,added_user,modified_user,enabled)
 VALUES (UUID(),'研发部','00000000-0000-0000-0000-000000000000','15145033859',NULL,@company_id,NOW(),NOW(),NULL,NULL,1);
