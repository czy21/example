#!/usr/bin/env python3
from shell import env_common

env_common.default_common.param_main_db_host = "127.0.0.1"

# mysql
env_common.default_common.param_main_db_mysql_port = "3306"

# mongo
env_common.default_common.param_main_db_mongo_port = "27017"

# neo4j
env_common.default_common.param_main_db_neo4j_port = "7687"