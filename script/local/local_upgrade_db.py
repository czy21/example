# !/usr/bin/env python
import os
import re
import sys

sys.path.append("..")
from subprocess import Popen, PIPE
from default.basic_config import mysql_select_release_config, mysql_cmd, mysql_import_sql_file
from default.local_default import local_mysql, local_mysql_user
from default.path_default import db_version_path
from local.local_build_db import built_version_sql
from default import font_color
from default.font_color import printWithColor

release_config = mysql_select_release_config(local_mysql.db_bak_name, local_mysql_user)
db_version_value = re.findall("\d+", str(Popen(release_config, stdout=PIPE, stderr=PIPE).stdout.readline()))[0]
phy_max_db_dir = max(os.listdir(db_version_path))


def build_upgrade_cmd():
    if phy_max_db_dir[: -1] > db_version_value:
        db_dir_val = built_version_sql(phy_max_db_dir)
        return mysql_cmd(local_mysql.db_name, local_mysql_user) + mysql_import_sql_file(db_dir_val, os.path.basename(__file__))
    return None


if __name__ == '__main__':
    if build_upgrade_cmd().strip() is not None:
        printWithColor('starting upgrade db', font_color.GREEN)
        printWithColor(build_upgrade_cmd(), font_color.DARKSKYBLUE)
        os.system(build_upgrade_cmd())
        printWithColor('finished upgrade db', font_color.GREEN)
        os.system("pause")
