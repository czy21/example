# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.temp_path import temp_operation_path


def run():
    os.system('ssh erp@prod_erp "rm -rf ./web/operation/*"')
    os.system("scp -r " + temp_operation_path + " erp@prod_erp:web")


if __name__ == '__main__':
    run()
