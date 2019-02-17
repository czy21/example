# !/usr/bin/env python
import os, re, sys

sys.path.append("..")
from subprocess import Popen, PIPE
from default.basic_config import mysql_select_release_config, mysql_migrate_db, mysql_cmd, mysql_import_sql_file, \
    mysql_create_db
from default.local_default import local_mysql, local_mysql_user
from default.path_default import db_version_path
from local.local_build_db import built_version_sql

create_db_cmd = mysql_create_db(local_mysql_user, "erp")
dump_cmd = mysql_migrate_db(local_mysql.db_bak_name, local_mysql_user, local_mysql.db_name, local_mysql_user)
os.system(create_db_cmd)
os.system(dump_cmd);

release_config = mysql_select_release_config(local_mysql.db_bak_name, local_mysql_user)
db_version_value = re.findall("\d+", str(Popen(release_config, stdout=PIPE, stderr=PIPE).stdout.readline()))[0]
phy_max_db_dir = max(os.listdir(db_version_path))
if phy_max_db_dir.endswith("v"):
    if phy_max_db_dir[: -1] > db_version_value:
        db_dir_val = built_version_sql(phy_max_db_dir)
        upgrade_cmd = mysql_cmd(local_mysql.db_name, local_mysql_user) + mysql_import_sql_file(db_dir_val, os.path.basename(__file__))
        os.system(upgrade_cmd)
