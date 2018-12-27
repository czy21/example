# !/usr/bin/env python
import os
import shutil

from default.basic_config import api_build_shell
from default.path_default import api_path, temp_api_path

os.chdir(api_path)
os.system(api_build_shell)
target = os.curdir + "\\target"
shutil.rmtree(temp_api_path)
shutil.copytree(target, temp_api_path)
