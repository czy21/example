# !/usr/bin/env python
import os
import shutil
import sys

sys.path.append("..")
from default.basic_config import api_build_cmd, api_revert_cmd, api_commit_cmd, api_update_cmd
from default.path_default import api_path, with_param_api_path
from default.temp_path import temp_api_path


def to_temp():
    os.system(api_build_cmd + with_param_api_path('-f'))
    shutil.rmtree(temp_api_path)
    shutil.copytree(api_path + "\\target", temp_api_path)
    return


def update_version(version_value):
    update_cmd = api_update_cmd + version_value + with_param_api_path('-f')
    commit_cmd = api_commit_cmd + with_param_api_path('-f')
    is_success = os.system(update_cmd + " && " + commit_cmd)
    if is_success != 0:
        os.system(api_revert_cmd + with_param_api_path('-f'))
    return


if __name__ == '__main__':
    to_temp()
    os.system("pause")
