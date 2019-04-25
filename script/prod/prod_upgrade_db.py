# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.basic_config import mysql
from default.prod_default import prod_mysql, prod_mysql_user_param

db_bak_version = mysql(t_db=prod_mysql.db_bak_name, t_user_param=prod_mysql_user_param).select_release_config()


def run(version):
    mysql(t_db=prod_mysql.db_name, t_user_param=prod_mysql_user_param).upgrade_db(__file__, db_bak_version, version)


if __name__ == '__main__':
    db_version = input("please input db version: ")
    run(db_version)
    os.system("pause")
