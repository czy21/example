# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.basic_config import mysql_handler
from default.prod_default import prod_mysql, prod_mysql_user_param
from default import font_color
from default.font_color import printWithColor

db_bak_version = mysql_handler(t_db=prod_mysql.db_bak_name, t_user_param=prod_mysql_user_param).select_release_config()


def run(version, exec_upgrade):
    if exec_upgrade:
        mysql_handler(t_db=prod_mysql.db_name, t_user_param=prod_mysql_user_param).upgrade_db(__file__, db_bak_version,version)
    else:
        mysql_handler(t_db=prod_mysql.db_name, t_user_param=prod_mysql_user_param).update_release_config(version)


if __name__ == '__main__':
    db_version = input("please input db version: ")
    line = input("execute upgrade db (1.true 2.false default true)")
    if line == "1":
        run(db_version, True)
    elif line == "2":
        printWithColor('no execute upgrade db', font_color.GREEN)
    os.system("pause")
