#!/usr/bin/env python3
from pathlib import Path

from script.domain.default import common as default_common, path as default_path
from script.utility import list as list_util
from script.utility import path as path_util

shell_path = Path.cwd().parent
default_common.param_main_db_name = "erp_java"
default_common.param_main_db_bak_name = default_common.param_main_db_name + "_bak"

# db user password
default_common.param_main_db_mysql_user = default_common.param_main_db_mongo_user = "admin"
default_common.param_main_db_mysql_pass = default_common.param_main_db_mongo_pass = default_common.param_main_db_neo4j_pass = "***REMOVED***"

default_common.param_main_db_neo4j_user = "neo4j"

# api
default_common.param_api_gradle_init_script_file_path = path_util.pure_path_join(shell_path, "template", "init.gradle")
default_common.param_api_extra_config_template_name = path_util.pure_path_join(shell_path, "template", "build.extra.gradle")
default_common.param_api_yml_override_template_name = path_util.pure_path_join(shell_path, "template", "application-override.yml")
default_common.param_api_root_project_path = path_util.pure_path_join(default_path.project_code_api, "portal")
default_common.param_api_docker_gradle_command = list_util.arr_param_to_str(["sudo docker run --rm -u gradle",
                                                                             "-v \"$HOME\":\"$HOME\"",
                                                                             "gradle:jdk11"])

# db
default_common.param_main_db_mysql_file_path = path_util.pure_path_join(default_common.param_main_db_mysql_file_path, "1_version")
default_common.param_main_db_neo4j_db_name = "neo4j"
