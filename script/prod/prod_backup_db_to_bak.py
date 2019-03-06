# !/usr/bin/env python
import os
import sys

sys.path.append("..")

from default.prod_default import prod_mysql, prod_mysql_user
from default.basic_config import mysql_restore_db
from default import font_color
from default.font_color import printWithColor

backup_db_cmd = mysql_restore_db(prod_mysql.db_name, prod_mysql_user, prod_mysql.db_bak_name, prod_mysql_user)

if __name__ == '__main__':
    printWithColor('starting backup db', font_color.GREEN)
    printWithColor(backup_db_cmd, font_color.DARKSKYBLUE)
    os.system(backup_db_cmd)
    printWithColor('finished backup db', font_color.GREEN)
    os.system("pause")
