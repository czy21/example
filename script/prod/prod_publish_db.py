# !/usr/bin/env python

import os
import sys

sys.path.append("..")
from default.basic_config import update_release_config_sql, mysql_cmd, import_sql_file
from default.prod_default import prod, prod_user
from default.path_default import db_version_path
from local.local_build_version_sql import built_version_sql


def db_update(version):
    update_release_config = update_release_config_sql(prod.db_name, prod_user, version)
    if os.path.exists(db_version_path + "\\" + version + "v"):
        os.system("python ./prod_backup_db_to_bak.py")
        os.system("python ./prod_restore_db.py")
        upgrade_cmd = mysql_cmd(prod.db_name, prod_user) \
                      + import_sql_file(built_version_sql(version + "v"), os.path.basename(__file__))
        os.system(upgrade_cmd + "&&" + update_release_config)
    else:
        os.system(update_release_config)
