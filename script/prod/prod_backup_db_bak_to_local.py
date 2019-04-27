# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.basic_config import mysql_handler
from default.local_default import local_mysql, local_mysql_user_param
from default.prod_default import prod_mysql, prod_mysql_user_param


def run():
    source_dump_db = mysql_handler(t_db=prod_mysql.db_bak_name, t_user_param=prod_mysql_user_param).obtain_dump_cmd()
    mysql_handler(t_db=local_mysql.db_bak_name, t_user_param=local_mysql_user_param).restore_db(source_dump_db)


if __name__ == '__main__':
    run()
    os.system("pause")
