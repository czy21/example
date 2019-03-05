set @role_id=(select role_id from sys_role where role_name='管理员' limit 1);

set @seq_value = (select sequence_value from worker_node order by modified_time desc limit 1);

set @function_id=(select function_id from sys_function limit 1);

INSERT INTO sys_role_function(`role_function_id`,`role_id`, `function_id`, `added_time`, `modified_time`, `added_user`, `modified_user`) 
SELECT @seq_value,@role_id,@function_id,now(),now(),null,null
FROM DUAL WHERE NOT EXISTS (
    SELECT 1 FROM sys_role_function LIMIT 1
);

INSERT INTO sys_role_function(`role_id`, `function_id`, `added_time`, `modified_time`, `added_user`, `modified_user`)
select r.role_id,f.function_id,now(),now(),null,null from sys_role r
cross join sys_function f 
left join sys_role_function rf on rf.role_id = r.role_id and rf.function_id = f.function_id
where r.role_name='管理员' and rf.role_function_id is null;