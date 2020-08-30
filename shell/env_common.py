# !/usr/bin/env python
from script.domain import default_common
from script.utility import path as path_util

default_common.param_main_db_name = "erp_java"
default_common.param_main_db_bak_name = default_common.param_main_db_name + "_bak"

default_common.param_main_db_mysql_user = default_common.param_main_db_mongo_user = "admin"
default_common.param_main_db_mysql_pass = default_common.param_main_db_mongo_pass = "***REMOVED***"
default_common.param_api_extra_config_template_name = path_util.pure_path_join(__file__, "template/build.extra.gradle")
default_common.param_api_yml_override_template_name = path_util.pure_path_join(__file__, "template/application-override.yml")
default_common.param_api_root_project_path = path_util.pure_path_join(default_common.path_default.project_code_api, "portal")
default_common.param_main_db_mysql_file_path = path_util.pure_path_join(default_common.param_main_db_mysql_file_path, "1_version")
