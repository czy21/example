# !/usr/bin/env python

import os
import sys

sys.path.append("..")
from default.basic_config import mysql_update_release_config, mysql_cmd, mysql_import_sql_file
from default.prod_default import prod_mysql, prod_mysql_user
from default.path_default import db_version_path
from local.local_build_db import built_version_sql


def db_update(version):
    update_release_config = mysql_update_release_config(prod_mysql.db_name, prod_mysql_user, version)
    if os.path.exists(db_version_path + "\\" + version + "v"):
        os.system("python ./prod_backup_db_to_bak.py")
        os.system("python ./prod_restore_db.py")
        upgrade_cmd = mysql_cmd(prod_mysql.db_name, prod_mysql_user) \
                      + mysql_import_sql_file(built_version_sql(version + "v"), os.path.basename(__file__))
        os.system(upgrade_cmd + "&&" + update_release_config)
    else:
        os.system(update_release_config)


if __name__ == '__main__':
    db_version = input("please input db version: ")
    db_update(db_version)
    os.system("pause")
