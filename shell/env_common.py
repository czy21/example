#!/usr/bin/env python3

from domain.default import path as default_path
from utility import path as path_util

shell_path = path_util.join_path(default_path.root_path, "shell")

param_project_name = "demo"

# api
param_api_root_project_path = path_util.join_path(default_path.project_code_api)

# web
param_web_root_project_path = path_util.join_path(default_path.project_code_web, "react", "operation")

param_main_db_mysql_user = \
    param_main_db_mongo_user = "admin"

param_main_db_mssql_user = "sa"

param_main_db_mysql_pass = \
    param_main_db_pgsql_pass = \
    param_main_db_mssql_pass = \
    param_main_db_chsql_pass = \
    param_main_db_mongo_pass = \
    param_main_db_neo4j_pass = "Czy.190815"

param_main_db_neo4j_user = "neo4j"
param_main_db_pgsql_user = "postgres"
param_main_db_chsql_user = "default"

param_main_db_mysql_file_path = path_util.join_path(default_path.project_db, "mysql", "1_version")
# param_main_db_mysql_file_path = path_util.join_path("C:/Users/zhaoyu.chen/Desktop/developer/mx-project/v2.7.0/")
param_main_db_pgsql_file_path = path_util.join_path(default_path.project_db, "pgsql", "1_version")
param_main_db_mssql_file_path = path_util.join_path(default_path.project_db, "mssql", "1_version")
param_main_db_chsql_file_path = path_util.join_path(default_path.project_db, "chsql", "1_version")
param_main_db_mongo_file_path = path_util.join_path(default_path.project_db, "mongo", "1_version")
param_main_db_neo4j_file_path = path_util.join_path(default_path.project_db, "neo4j", "1_version")
