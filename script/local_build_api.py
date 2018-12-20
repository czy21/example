# !/usr/bin/env python
import os
import glob
import shutil
from common import api_path, api_build_shell, temp_api_path

os.chdir(api_path)
os.system(api_build_shell)
target = os.curdir + "\\target"
os.chdir(target)
release = glob.glob("erp-*.jar")[0]
shutil.copy(release, temp_api_path)
