# !/usr/bin/env python
import os
import re
import sys

sys.path.append("..")
from subprocess import Popen, PIPE

from default.basic_config import update_release_config_sql, select_release_config_sql, migrate_db_sql, mysql_cmd, import_sql_file
from default.local_default import local, local_user
from default.path_default import temp_db_path, version_path

dump_cmd = migrate_db_sql(local.db_bak_name, local_user, local.db_name, local_user)
os.system(dump_cmd)
release_config = select_release_config_sql(local.db_bak_name, local_user)
db_version_value = re.findall("\d+", str(Popen(release_config, stdout=PIPE, stderr=PIPE).stdout.readline()))[0]
phy_max_db_path = max(os.listdir(version_path))

if phy_max_db_path.endswith("v"):
    if phy_max_db_path[: -1] > db_version_value:
        max_version_dir_name = phy_max_db_path[:-1]
        all_sql = open(temp_db_path + "\\all_in_one.sql", "w+", encoding="utf8")
        for r, s, fs in os.walk(version_path + "\\" + phy_max_db_path):
            if r[-3:] == "(s)":
                for r, s, fs in os.walk(r):
                    for f in fs:
                        structure_sql = open(r + "\\" + f, 'r', encoding="utf-8")
                        all_sql.write(structure_sql.read() + "\n")
                        structure_sql.close()
            if r[-3:] == "(i)":
                for r, s, fs in os.walk(r):
                    for f in fs:
                        init_data_sql = open(r + "\\" + f, 'r', encoding="utf-8")
                        all_sql.write(init_data_sql.read())
                        init_data_sql.close()
        all_sql.close()
        upgrade_cmd = mysql_cmd(local.db_name, local_user) + import_sql_file(all_sql.name, os.path.basename(__file__))
        update_release_config = update_release_config_sql(local_user, phy_max_db_path[:-1])
        os.system(upgrade_cmd + " && " + update_release_config)
