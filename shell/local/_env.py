#!/usr/bin/env python3
from shell import env_common
from script.domain.default import path as default_path
from script.utility import path as path_util

param_env_suffix = "local"

db_host = "192.168.2.3"

# redis
param_main_redis_host = db_host
param_main_redis_port = "6379"
param_main_redis_pass = "***REMOVED***"

# mysql
param_main_db_mysql_host = db_host
param_main_db_mysql_port = "3306"

# mongo
param_main_db_mongo_host = db_host
param_main_db_mongo_port = "27017"

# neo4j
param_main_db_neo4j_host = db_host
param_main_db_neo4j_port = "7687"

param_api_docker_gradle_command = False
