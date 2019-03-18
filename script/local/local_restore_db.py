# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.basic_config import mysql_obj
from default.local_default import local_mysql, local_mysql_user

# restore_db_cmd = mysql_restore_db(local_mysql.db_bak_name, local_mysql_user, local_mysql.db_name, local_mysql_user)

if __name__ == '__main__':
    mysql_obj(t_host='localhost', t_port='3306', t_db='erp', t_user='root', t_pwd='sasa').update_release_config('109')
    # restore_db_cmd.execute()
    os.system("pause")
