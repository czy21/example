#!/usr/bin/python3
import os
from default.path_default import temp_operation_path
from default.basic_config import erp_home

os.system("python ./local_build_op.py")
os.system('ssh erp "cd web/operation && rm -rf *"')
op_publish = "scp -r " + temp_operation_path + " erp:" + erp_home + "/web"
os.system(op_publish)
