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


def run(version):
    update_release_config = mysql_update_release_config(prod_mysql.db_name, prod_mysql_user, version)
    upgrade_db_cmd = mysql_cmd(prod_mysql.db_name, prod_mysql_user) + mysql_import_sql_file(built_version_sql(version + "v"), os.path.basename(__file__))
    printWithColor('db backuping', font_color.GREEN)
    printWithColor(prod_backup_db_to_bak.backup_db_cmd, font_color.DARKSKYBLUE)
    os.system(prod_backup_db_to_bak.backup_db_cmd)
    printWithColor('db backuped', font_color.GREEN)
    printWithColor('db restoring', font_color.GREEN)
    printWithColor(prod_restore_db.restore_db_cmd, font_color.DARKSKYBLUE)
    os.system(prod_restore_db.restore_db_cmd)
    printWithColor('db restored', font_color.GREEN)
    if os.path.exists(db_version_path + "\\" + version + "v"):
        printWithColor('db upgrading', font_color.GREEN)
        printWithColor(upgrade_db_cmd, font_color.DARKSKYBLUE)
        os.system(upgrade_db_cmd)
        printWithColor('db upgraded', font_color.GREEN)

    printWithColor('updating db version', font_color.GREEN)
    printWithColor(update_release_config, font_color.DARKSKYBLUE)
    os.system(update_release_config)
    printWithColor('updated db version', font_color.GREEN)


if __name__ == '__main__':
    db_version = input("please input db version: ")
    run(db_version)
    os.system("pause")
