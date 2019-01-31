# !/usr/bin/env python
import os, re, sys

sys.path.append("..")
from subprocess import Popen, PIPE
from default.basic_config import select_release_config_sql, migrate_db_sql, mysql_cmd, import_sql_file
from default.local_default import local, local_user
from default.path_default import db_version_path
from local.local_build_version_sql import built_version_sql

dump_cmd = migrate_db_sql(local.db_bak_name, local_user, local.db_name, local_user)
os.system(dump_cmd)
release_config = select_release_config_sql(local.db_bak_name, local_user)
db_version_value = re.findall("\d+", str(Popen(release_config, stdout=PIPE, stderr=PIPE).stdout.readline()))[0]
phy_max_db_dir = max(os.listdir(db_version_path))

if phy_max_db_dir.endswith("v"):
    if phy_max_db_dir[: -1] > db_version_value:
        db_dir_val = built_version_sql(phy_max_db_dir)
        upgrade_cmd = mysql_cmd(local.db_name, local_user) + import_sql_file(db_dir_val, os.path.basename(__file__))
        os.system(upgrade_cmd)
