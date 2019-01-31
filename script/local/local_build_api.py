# !/usr/bin/env python
import os
import shutil
import sys

sys.path.append("..")
from default.basic_config import api_build_shell, api_revert_shell, api_commit_shell, api_update_shell
from default.path_default import api_path
from default.temp_path import temp_api_path


def build_to_temp():
    os.system(api_build_shell + " -f " + api_path)
    shutil.rmtree(temp_api_path)
    shutil.copytree(api_path + "\\target", temp_api_path)
    return


def update_api_version(version):
    update_cmd = api_update_shell + version + " -f " + api_path
    commit_cmd = api_commit_shell + " -f " + api_path
    is_success = os.system(update_cmd + " && " + commit_cmd)
    if is_success != 0:
        os.system(api_revert_shell + " -f " + api_path)
    return


if __name__ == '__main__':
    build_to_temp()
