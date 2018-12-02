# !/usr/bin/env python
import os
import shutil
from common import operation_path, web_build_shell, temp_operation_path

os.chdir(operation_path)
os.system(web_build_shell)
dist = os.curdir + "\\dist"
shutil.rmtree(temp_operation_path)
shutil.copytree(dist, temp_operation_path)
