#!/usr/bin/env python3

from script.domain.default import common as default_common, path as default_path
from script.utility import collection as list_util
from script.utility import path as path_util

shell_path = path_util.pure_path_join(default_path.root_path, "shell")

param_project_name="erp"

param_api_host_port = param_api_container_port = 8080
param_web_container_name = "nginx"

script_template_java = path_util.pure_path_join(default_path.script_template, "java")
param_api_root_project_path = path_util.pure_path_join(default_path.project_code_api)
param_api_resource_output_path = path_util.pure_path_join(default_path.output_api, "resources")

# compose
api_compose_name = "docker-compose-api.yml"
api_compose_file_template_path = path_util.pure_path_join(default_path.script_template, api_compose_name)
param_api_compose_file_output_path = path_util.pure_path_join(default_path.output_api, api_compose_name)

# jmx config name
param_api_jmv_config_name = "jmx-config.yml"
api_jmx_config_template_path = path_util.pure_path_join(script_template_java, param_api_jmv_config_name)
api_jmx_config_output_path = path_util.pure_path_join(param_api_resource_output_path, param_api_jmv_config_name)

# extra config
gradle_extra_config_name = "build.extra.gradle"
param_api_gradle_extra_config_template_path = path_util.pure_path_join(script_template_java, gradle_extra_config_name)
param_api_gradle_extra_config_output_path = path_util.pure_path_join(default_path.output_tmp, gradle_extra_config_name)

# api_config
api_config_name = "application-override.yml"
api_config_file_template_path = path_util.pure_path_join(shell_path, "template", api_config_name)
param_api_config_file_output_path = path_util.pure_path_join(param_api_resource_output_path, api_config_name)

# dockerfile
dockerfile_name = "Dockerfile"
api_dockerfile_template_path = path_util.pure_path_join(script_template_java, dockerfile_name)
param_api_dockerfile_output_path = path_util.pure_path_join(default_path.output_api, dockerfile_name)

param_template_output_dict = {
    api_config_file_template_path: param_api_config_file_output_path,
    api_compose_file_template_path: param_api_compose_file_output_path,
    api_dockerfile_template_path: param_api_dockerfile_output_path,
    api_jmx_config_template_path: api_jmx_config_output_path
}

param_main_db_mysql_user = param_main_db_mongo_user = "admin"
param_main_db_mysql_pass = param_main_db_mongo_pass = param_main_db_neo4j_pass = "Czy.190815"

db_host = "192.168.2.3"

# rabbitmq
param_main_rabbitmq_address = "root:Czy.190815@" + db_host + ":5672"

param_main_db_mysql_file_path = path_util.pure_path_join(default_path.project_db, "mysql", "1_version")
param_main_db_neo4j_user = "neo4j"
param_main_db_neo4j_file_path = path_util.pure_path_join(default_path.project_db, "neo4j")
