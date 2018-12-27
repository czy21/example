# !/usr/bin/env python
import os
from default.path_default import temp_api_path
from default.basic_config import erp_home

os.system("python ../local/local_build_api.py")
os.system('ssh erp "cd api && ./restart.sh -d"')
os.system("scp -r " + temp_api_path + " erp:" + erp_home)
os.system('ssh erp "cd api && ./restart.sh -u"')
