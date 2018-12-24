# !/usr/bin/env python
import os
import glob
import shutil
from default.path_default import api_path, temp_api_path
from default.basic_config import api_build_shell

os.chdir(api_path)
os.system(api_build_shell)
target = os.curdir + "\\target"
os.chdir(target)
release = glob.glob("erp-*.jar")[0]
shutil.copy(release, temp_api_path)
