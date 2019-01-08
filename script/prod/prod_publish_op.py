# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.temp_path import temp_operation_path
from local import local_build_op


def run():
    local_build_op.build_to_temp()
    os.system('ssh erp@erp "cd web/operation && rm -rf *"')
    os.system("scp -r " + temp_operation_path + " erp@erp:web")


if __name__ == '__main__':
    run()
