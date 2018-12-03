# !/usr/bin/env python
import os
from common import temp_api_path, erp_home

# os.system("python ./local_build_api.py")
# api_publish = "scp -r " + temp_api_path + " erp:" + erp_home
# os.system(api_publish)
api_restart='ssh erp "cd api && ./test.sh --apiName erp"'
os.system(api_restart)
