# !/usr/bin/env python

import os, sys

sys.path.append("..")
from default.basic_config import update_release_config_sql, mysql_cmd, import_sql_file
from default.prod_default import prod, prod_user
from default.path_default import version_path
from local.local_build_version_sql import built_version_sql_path

db_version = input("please input db dir version: ")

if db_version.endswith("v"):
    update_release_config = update_release_config_sql(prod_user, db_version[:-1])
    if os.path.exists(version_path + "\\" + db_version):
        os.system("python ./prod_backup_db_to_bak.py")
        os.system("python ./prod_restore_db.py")
        upgrade_cmd = mysql_cmd(prod.db_name, prod_user) \
                      + import_sql_file(built_version_sql_path(db_version), os.path.basename(__file__))
        os.system(update_release_config + "&&" + update_release_config)
    else:
        os.system(update_release_config)
