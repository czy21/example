#!/usr/bin/env python3

from script.domain.default import common as default_common, path as default_path
from script.utility import collection as list_util
from script.utility import path as path_util

shell_path = path_util.pure_path_join(default_path.root_path, "shell")

param_project_name = "erp"
param_api_host_port = param_api_container_port = 8080
param_web_container_name = "nginx"

# api
param_api_archive_file_name = "api.jar"
param_api_plugin_file_path = path_util.pure_path_join(default_path.project_plugin, "build.gradle")
param_api_gradle_init_script_file_path = path_util.pure_path_join(default_path.script_template, "init.gradle")

# extra config
gradle_extra_config_name = "build.extra.gradle"
param_api_gradle_extra_config_template_path = path_util.pure_path_join(default_path.script_template, gradle_extra_config_name)
param_api_gradle_extra_config_output_path = path_util.pure_path_join(default_path.output_tmp, gradle_extra_config_name)

param_api_resource_output_path = path_util.pure_path_join(default_path.output_api, "resource")

# api_config
api_config_name = "application-override.yml"
api_config_file_template_path = path_util.pure_path_join(shell_path, "template", api_config_name)
param_api_config_file_output_path = path_util.pure_path_join(param_api_resource_output_path, api_config_name)

# compose
api_compose_name = "docker-compose-api.yml"
api_compose_template_path = path_util.pure_path_join(default_path.script_template, api_compose_name)
param_api_compose_output_path = path_util.pure_path_join(default_path.output_api, api_compose_name)

# dockerfile
dockerfile_name = "Dockerfile"
api_dockerfile_template_path = path_util.pure_path_join(default_path.script_template, dockerfile_name)
param_api_dockerfile_output_path = path_util.pure_path_join(default_path.output_api, dockerfile_name)

param_template_output_dict = {
    api_config_file_template_path: param_api_config_file_output_path,
    api_compose_template_path: param_api_compose_output_path,
    api_dockerfile_template_path: param_api_dockerfile_output_path
}

param_api_root_project_path = path_util.pure_path_join(default_path.project_code_api)

# network
param_api_network_containers = ["jenkins", "mysql", "mongo", "redis", "rabbitmq", "nginx"]
param_api_network_name = "erp_play_default"

# web
cp_static = "nginx.sh"
nginx_conf = "nginx.conf"
param_web_root_project_path = path_util.pure_path_join(default_path.project_code_web, "react/operation")
param_web_cp_template_path = path_util.pure_path_join(shell_path, "template", cp_static)
param_web_cp_output_file_path = path_util.pure_path_join(default_path.output_tmp, cp_static)
param_web_nginx_template_path = path_util.pure_path_join(shell_path, "template", nginx_conf)

param_web_env_template_path = path_util.pure_path_join(shell_path, "template", "react.app.env")
param_web_env_path = path_util.pure_path_join(default_path.project_code_web, "react/operation/.env")

# db
param_main_redis_host = "redis"
param_main_rabbit_host = "rabbitmq"

param_main_db_mysql_host = "mysql"
param_main_db_mongo_host = "mongo"
param_main_db_neo4j_host = "neo4j"
param_main_db_mysql_user = param_main_db_mongo_user = "admin"
param_main_db_mysql_pass = param_main_db_mongo_pass = param_main_db_neo4j_pass = "***REMOVED***"

param_main_db_mysql_file_path = path_util.pure_path_join(default_path.project_db, "mysql", "1_version")
param_main_db_neo4j_user = "neo4j"
param_main_db_neo4j_file_path = path_util.pure_path_join(default_path.project_db, "neo4j")
