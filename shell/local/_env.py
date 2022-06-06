#!/usr/bin/env python3
from shell import env_common

param_env_suffix = "local"

qualify_project_env = "_".join([env_common.param_project_name, param_env_suffix])
param_main_db_name = qualify_project_env
param_main_db_bak_name = "_".join([param_main_db_name, "bak"])

db_host = "192.168.2.18"

# redis
param_main_redis_host = db_host
param_main_redis_port = "6379"
param_main_redis_pass = "Czy.190815"

# mysql
param_main_db_mysql_host = db_host
param_main_db_mysql_port = "3306"

# pgsql
param_main_db_pgsql_host = db_host
param_main_db_pgsql_port = "5432"

# mssql
param_main_db_mssql_host = db_host
param_main_db_mssql_port = "1433"

# cksql
param_main_db_cksql_host = "192.168.2.25"
param_main_db_cksql_port = "9004"

# mongo
param_main_db_mongo_host = db_host
param_main_db_mongo_port = "27017"

# neo4j
param_main_db_neo4j_host = db_host
param_main_db_neo4j_port = "7687"
