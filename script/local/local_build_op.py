# !/usr/bin/env python
import os
import shutil
from default.path_default import operation_path, temp_operation_path
from default.basic_config import web_build_shell

os.chdir(operation_path)
os.system(web_build_shell)
dist = os.curdir + "\\dist"
shutil.rmtree(temp_operation_path)
shutil.copytree(dist, temp_operation_path)
