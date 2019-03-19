# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.basic_config import mysql
from default.local_default import local_mysql, local_mysql_user_param

db_bak_version = mysql(t_db=local_mysql.db_bak_name, t_user_param=local_mysql_user_param).select_release_config()


def run():
    mysql(t_db=local_mysql.db_name, t_user_param=local_mysql_user_param).upgrade_db(__file__, db_bak_version)


if __name__ == '__main__':
    run()
    os.system("pause")
