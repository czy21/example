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

# mysql
param_main_db_mysql_port = "3306"

# mongo
param_main_db_mongo_port = "27017"

# neo4j
param_main_db_neo4j_port = "7687"
