-- 创建指定库中所有表的主键不是自增的sql语句
select 
CONCAT('ALTER TABLE `',TABLE_NAME,'` MODIFY COLUMN `',COLUMN_NAME,'` ',COLUMN_TYPE,' NOT NULL AUTO_INCREMENT FIRST;') as alterSQL
from  information_schema.`COLUMNS` 
where TABLE_SCHEMA = 'erp' and TABLE_NAME like 'sys_%'
and IS_NULLABLE = 'NO' and COLUMN_KEY in ('PRI') and EXTRA not in ('auto_increment')
and COLUMN_DEFAULT is null