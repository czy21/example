# !/usr/bin/env python
import os
from common import temp_operation_path, erp_home

os.system("python ./local_build_op.py")
os.system('ssh erp "cd web/operation && rm -rf *"')
op_publish = "scp -r " + temp_operation_path + " erp:" + erp_home + "/web"
os.system(op_publish)
