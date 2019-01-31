# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.basic_config import erp_home
from default.temp_path import temp_api_path


def run():
    os.system('ssh erp@prod_erp "cd api && ./api-restart.sh -d"')
    os.system("scp -r " + temp_api_path + " erp@prod_erp:" + erp_home)
    os.system('ssh erp@prod_erp "cd api && ./api-restart.sh -u"')

if __name__ == '__main__':
    run()
