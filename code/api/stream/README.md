```shell
# 500000 batchSize:500 4m22s
select TIMEDIFF(max(`timestamp`),min(`timestamp`)) from user_1 u
```