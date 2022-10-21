```shell
# mysql
# 500000 batchSize:500 4m22s
select TIMEDIFF(max(`timestamp`),min(`timestamp`)) from user_1 u

# chsql
# 500000 batchSize:500 4m22s
select dateDiff('minute',max(`timestamp`),min(`timestamp`)) from user_1 u

# crate crash 
cat xxx.sql | crash --hosts 192.168.2.18:4201

# clickhouse
clickhouse-client --query "select name from default.user_1" --format SQLInsert --output_format_sql_insert_max_batch_size 5000 | sed -e 's|table|user_1|g' -e 's|`||g' > /var/lib/clickhouse/data/user_1.sql
```