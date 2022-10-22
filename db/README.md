```sql

-- delete repeat id_num data
delete from customer
where id in ( select t.id from (select min(i.id) as id from customer i group by i.id_num having count(0) > 1) t )
```