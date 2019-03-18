# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.local_default import local_mysql, local_mysql_user
from default.path_default import backup_path

if not os.path.exists(backup_path):
    os.makedirs(backup_path)
local_mysql_user._t_db = local_mysql.db_name
dump_cmd = local_mysql_user.dump_target_db() + " --skip-add-locks --quick -t " + " > " + backup_path + "\\" + local_mysql.db_name + "_bak" ".sql"
print(dump_cmd)
os.system(dump_cmd)
