#!/usr/bin/env python3
from pathlib import Path

from script.domain.default import common as default_common, path as default_path
from script.utility import list as list_util
from script.utility import path as path_util

param_project_name = "erp"
param_env_suffix = ""
shell_path = Path.cwd().parent

# api
default_common.param_api_archive_file_name = "api.jar"
default_common.param_api_plugin_file_path = path_util.pure_path_join(default_path.project_plugin, "build.gradle")
default_common.param_api_gradle_init_script_file_path = path_util.pure_path_join(shell_path, "template", "init.gradle")
default_common.param_api_extra_config_template_name = path_util.pure_path_join(shell_path, "template", "build.extra.gradle")
default_common.param_api_yml_override_template_name = path_util.pure_path_join(shell_path, "template", "application-override.yml")
default_common.param_api_dockerfile_template_name = path_util.pure_path_join(shell_path, "template", "Dockerfile")
default_common.param_api_compose_template_name = path_util.pure_path_join(shell_path, "template", "docker-compose-api.yml")
default_common.param_api_dockerfile_output_file_path = default_path.output_api
default_common.param_api_compose_output_file_path = default_path.output_api
param_api_host_port = 8075
default_common.param_injected = {
    "param_api_host_port": param_api_host_port,
    "param_api_container_port": param_api_host_port
}

default_common.param_api_root_project_path = path_util.pure_path_join(default_path.project_code_api, "portal")
default_common.param_api_docker_gradle_command = list_util.arr_param_to_str(
    [
        "sudo docker run --rm --user root",
        "--volume \"$HOME\":\"$HOME\"",
        "--volume \"$HOME\"/.gradle:/home/gradle/.gradle",
        "gradle:jdk11"
    ])

# db
default_common.param_main_db_mysql_user = default_common.param_main_db_mongo_user = "admin"
default_common.param_main_db_mysql_pass = default_common.param_main_db_mongo_pass = default_common.param_main_db_neo4j_pass = "***REMOVED***"

default_common.param_main_db_mysql_file_path = path_util.pure_path_join(default_path.project_db, "mysql", "1_version")
default_common.param_main_db_neo4j_user = "neo4j"
default_common.param_main_db_neo4j_file_path = path_util.pure_path_join(default_path.project_db, "neo4j")


def inject():
    default_common.param_main_db_name = "_".join([param_project_name, "java", param_env_suffix])
    default_common.param_main_db_bak_name = "_".join([default_common.param_main_db_name, "bak"])
