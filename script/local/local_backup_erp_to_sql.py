# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.local_default import local_mysql, local_mysql_user_param
from default.path_default import backup_path
from default.basic_config import mysql

if not os.path.exists(backup_path):
    os.makedirs(backup_path)
dump_cmd = mysql(t_user_param=local_mysql_user_param, t_db=local_mysql.db_name).obtain_dump_cmd() \
           + " --skip-add-locks --quick -t " + " > " + backup_path + "\\" + local_mysql.db_name + "_bak" ".sql"
os.system(dump_cmd)
