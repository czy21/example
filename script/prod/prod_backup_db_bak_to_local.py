# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.basic_config import mysql_restore_db
from default.local_default import local_mysql, local_mysql_user
from default.prod_default import prod_mysql, prod_mysql_user
from default import font_color
from default.font_color import printWithColor

restore_db_cmd = mysql_restore_db(prod_mysql.db_bak_name, prod_mysql_user, local_mysql.db_bak_name, local_mysql_user)

if __name__ == '__main__':
    printWithColor('db restoring', font_color.GREEN)
    printWithColor(restore_db_cmd, font_color.DARKSKYBLUE)
    os.system(restore_db_cmd)
    printWithColor('db restored', font_color.GREEN)
    os.system("pause")
