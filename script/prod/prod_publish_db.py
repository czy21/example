# !/usr/bin/env python

import os
import sys

sys.path.append("..")
from default.basic_config import mysql_update_release_config, mysql_cmd, mysql_import_sql_file
from default.prod_default import prod_mysql, prod_mysql_user
from default.path_default import db_version_path
from local.local_build_db import built_version_sql
from prod import prod_backup_db_to_bak, prod_restore_db
from default import font_color
from default.font_color import printWithColor


def db_update(version):
    update_release_config = mysql_update_release_config(prod_mysql.db_name, prod_mysql_user, version)
    upgrade_db_cmd = mysql_cmd(prod_mysql.db_name, prod_mysql_user) + mysql_import_sql_file(built_version_sql(version + "v"), os.path.basename(__file__))
    printWithColor('starting backup db', font_color.GREEN)
    printWithColor(prod_backup_db_to_bak.backup_db_cmd, font_color.DARKSKYBLUE)
    os.system(prod_backup_db_to_bak.backup_db_cmd)
    printWithColor('finished backup db', font_color.GREEN)
    printWithColor('starting restore db', font_color.GREEN)
    printWithColor(prod_restore_db.restore_db_cmd, font_color.DARKSKYBLUE)
    os.system(prod_restore_db.restore_db_cmd)
    printWithColor('finished restore db', font_color.GREEN)
    if os.path.exists(db_version_path + "\\" + version + "v"):
        printWithColor('starting upgrade db', font_color.GREEN)
        printWithColor(upgrade_db_cmd, font_color.DARKSKYBLUE)
        os.system(upgrade_db_cmd)
        printWithColor('finished upgrade db', font_color.GREEN)

    printWithColor('starting update db version', font_color.GREEN)
    printWithColor(update_release_config, font_color.DARKSKYBLUE)
    os.system(update_release_config)
    printWithColor('finished update db version', font_color.GREEN)


if __name__ == '__main__':
    db_version = input("please input db version: ")
    db_update(db_version)
    os.system("pause")
