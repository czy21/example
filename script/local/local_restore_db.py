# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.basic_config import mysql
from default.local_default import local_mysql, local_mysql_user

source_dump_db = mysql(t_db=local_mysql.db_bak_name, t_user_param=local_mysql_user.t_user_param).obtain_dump_cmd()

if __name__ == '__main__':
    # local_mysql_user.restore_db(source_dump_db,False)
    print(local_mysql_user.restore_db(source_dump_db, False))
    # local_mysql_user.update_release_config('109')
    os.system("pause")
