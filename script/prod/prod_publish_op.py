# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.basic_config import erp_home
from default.temp_path import temp_operation_path
from local import local_build_op


def run():
    local_build_op.build_to_temp()
    os.system('ssh erp "cd web/operation && rm -rf *"')
    op_publish = "scp -r " + temp_operation_path + " erp:" + erp_home + "/web"
    os.system(op_publish)
