# !/usr/bin/env python
from pathlib import Path

from script.domain import default_common

default_common.param_main_db_user = "admin"
default_common.param_main_db_pass = "***REMOVED***"
default_common.param_api_extra_config_template_name = Path(__file__).parent.resolve().joinpath("template/build.extra.gradle").as_posix()
default_common.param_api_root_project_path = Path(default_common.path_default.project_code_api).joinpath("portal").as_posix()
