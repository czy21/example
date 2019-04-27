# !/usr/bin/env python
import os
import shutil
import sys

sys.path.append("..")
from default.path_default import operation_path
from default.basic_config import web_build_cmd
from default.temp_path import temp_operation_path


def to_temp():
    os.system("cd " + operation_path + " && " + web_build_cmd)
    shutil.rmtree(temp_operation_path)
    shutil.copytree(operation_path + "\\dist", temp_operation_path)
    return


if __name__ == '__main__':
    to_temp()
    os.system("pause")
