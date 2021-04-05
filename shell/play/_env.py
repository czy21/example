#!/usr/bin/env python3
from shell import env_common
from script.domain.default import path as default_path
from script.utility import path as path_util

param_env_suffix = "play"

qualify_project_env = "_".join([env_common.param_project_name, param_env_suffix])
param_main_db_name = qualify_project_env
param_main_db_bak_name = "_".join([param_main_db_name, "bak"])
param_web_nginx_env_file_name = qualify_project_env + ".conf"
param_web_nginx_output_file_path = path_util.pure_path_join(default_path.output_tmp, param_web_nginx_env_file_name)

param_api_network_name = "_".join([env_common.param_project_name, param_env_suffix, "default"])

db_host = "192.168.2.8"

# redis
param_main_redis_host = db_host
param_main_redis_port = "6379"
param_main_redis_pass = "***REMOVED***"

# rabbitmq
param_main_rabbit_host = db_host
param_main_rabbit_port = "5672"
param_main_rabbit_user = "root"
param_main_rabbit_pass = "***REMOVED***"

# mysql
param_main_db_mysql_host = db_host
param_main_db_mysql_port = "3306"

# mongo
param_main_db_mongo_host = db_host
param_main_db_mongo_port = "27017"

# neo4j
param_main_db_neo4j_host = db_host
param_main_db_neo4j_port = "7687"
