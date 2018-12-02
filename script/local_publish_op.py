# !/usr/bin/env python
import os
from common import temp_operation_path, erp_home

os.system("python ./local_build_op.py")
op_publish = "scp -r " + temp_operation_path + " erp:" + erp_home + "/web"
os.system(op_publish)
