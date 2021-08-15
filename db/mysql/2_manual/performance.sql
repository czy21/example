select
mpl.target,
avg(mpl.duration),
sum(mpl.duration),
max(mpl.sequence),
max(mpl.table_count),
mpl.batch_id,
max(mpl.created_date) created_date
from ent_migrate_performance_log mpl
where mpl.target = 'HbasePersistServiceImpl'
group by mpl.target,mpl.batch_id
order by created_date desc;

select
*
from ent_migrate_performance_log mpl
where mpl.target = 'HbasePersistServiceImpl'
order by mpl.target,mpl.created_date desc,mpl.sequence;

insert into ent_sale(id,access_type,from_institution_code,from_institution_code_format,from_institution_name,producer,product_amount,product_batch,product_code,product_code_format,product_name,product_price,product_quantity,product_quantity_format,product_spec,product_unit,product_unit_format,sale_date,to_institution_code,to_institution_code_format,to_institution_name)
select
UUID(),
access_type,from_institution_code,from_institution_code_format,from_institution_name,producer,product_amount,product_batch,product_code,product_code_format,product_name,product_price,product_quantity,product_quantity_format,product_spec,product_unit,product_unit_format,sale_date,to_institution_code,to_institution_code_format,to_institution_name
from ent_sale_1;

select
 GROUP_CONCAT(sc.column_name)
from information_schema.`COLUMNS` sc
where sc.table_schema = DATABASE() and sc.table_name = 'ent_sale'