DROP table IF EXISTS `temp_target_key`;
CREATE TABLE temp_target_key (
    id BIGINT null,
    temp_id varchar(36) null,
    target_table varchar(50) null
);
drop procedure if exists temp_table_primaryKey;
drop procedure if exists create_target_table_key;
delimiter //
create procedure temp_table_primaryKey(in keyName varchar(50),in tableName varchar(50))
begin
drop table if exists temp_id;
SET @SQLSTR1 = CONCAT('create table temp_id select A.',keyName,' as id from ',tableName,' as A');
PREPARE STMT1 FROM @SQLSTR1;
EXECUTE STMT1;
DEALLOCATE PREPARE STMT1;
end 
;
create procedure create_target_table_key(in work_id BIGINT,in tableName varchar(50))
begin
declare isDone int default 0;
declare tempId varchar(36);
declare folderIds cursor for select A.id from temp_id as A;
declare continue handler for not FOUND set isDone = 1;
open folderIds;
REPEAT
	FETCH folderIds into tempId;
	if not isDone then
	insert into temp_target_key (id,temp_id,target_table) values(work_id,tempId,tableName);
	set work_id=work_id+1;
	end if;
until isDone end repeat;
close folderIds;
end
//
delimiter ;

call temp_table_primaryKey('company_id','sys_company');
call create_target_table_key(2959224461124968457,'sys_company');

set @next_id=(select max(id)+1 from temp_target_key);
call temp_table_primaryKey('department_id','sys_department');
call create_target_table_key(@next_id,'sys_department');

set @next_id=(select max(id)+1 from temp_target_key);
call temp_table_primaryKey('menu_id','sys_menu');
call create_target_table_key(@next_id,'sys_menu');

set @next_id=(select max(id)+1 from temp_target_key);
call temp_table_primaryKey('role_id','sys_role');
call create_target_table_key(@next_id,'sys_role');

set @next_id=(select max(id)+1 from temp_target_key);
call temp_table_primaryKey('role_menu_id','sys_role_menu');
call create_target_table_key(@next_id,'sys_role_menu');

set @next_id=(select max(id)+1 from temp_target_key);
call temp_table_primaryKey('user_id','sys_user');
call create_target_table_key(@next_id,'sys_user');

set @next_id=(select max(id)+1 from temp_target_key);
call temp_table_primaryKey('user_role_id','sys_user_role');
call create_target_table_key(@next_id,'sys_user_role');

ALTER TABLE erp.sys_department DROP FOREIGN KEY fk_department_company;
ALTER TABLE erp.sys_role_menu DROP FOREIGN KEY fk_role_menu_menu;
ALTER TABLE erp.sys_role_menu DROP FOREIGN KEY fk_role_menu_role;
ALTER TABLE erp.sys_user DROP FOREIGN KEY fk_user_department;
ALTER TABLE erp.sys_user_role DROP FOREIGN KEY fk_user_role_role;
ALTER TABLE erp.sys_user_role DROP FOREIGN KEY fk_user_role_user;