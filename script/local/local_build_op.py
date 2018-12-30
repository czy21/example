# !/usr/bin/env python
import os, sys, shutil

sys.path.append("..")
from default.path_default import operation_path
from default.basic_config import web_build_shell
from default.temp_path import temp_operation_path


def build_to_temp():
    os.system("cd " + operation_path + " && " + web_build_shell)
    shutil.rmtree(temp_operation_path)
    shutil.copytree(operation_path + "\\dist", temp_operation_path)
    return
