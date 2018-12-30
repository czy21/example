# !/usr/bin/env python
import os, sys, shutil

sys.path.append("..")
from default.basic_config import api_build_shell, api_revert_shell, api_commit_shell, api_update_shell
from default.path_default import api_path
from default.temp_path import temp_api_path


def build_to_temp():
    os.system("cd " + api_path + " && " + api_build_shell)
    shutil.rmtree(temp_api_path)
    shutil.copytree(api_path + "\\target", temp_api_path)
    return


def update_version(version):
    is_success = os.system("cd " + api_path + " && " + api_update_shell + version + " && " + api_commit_shell)
    if is_success != 0:
        os.system("cd " + api_path + " && " + api_revert_shell)
    return


if __name__ == '__main__':
    build_to_temp()
