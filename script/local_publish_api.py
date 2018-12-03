# !/usr/bin/env python
import os
from common import temp_api_path, erp_home

os.system("python ./local_build_api.py")
os.system('ssh erp "cd api && ./restart.sh -d"')
os.system("scp -r " + temp_api_path + " erp:" + erp_home)
os.system('ssh erp "cd api && ./restart.sh -u"')