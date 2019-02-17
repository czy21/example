# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.local_default import local_mysql, local_mysql_user
from default.path_default import backup_path
from default.basic_config import mysqldump_cmd

if not os.path.exists(backup_path):
    os.makedirs(backup_path)

dump_cmd = mysqldump_cmd(local_mysql.db_name, local_mysql_user) + "--skip-add-locks --quick -t " + " > " + backup_path + "\\" + local_mysql.db_name + "_bak" ".sql"
os.system(dump_cmd)