# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.basic_config import mysql_migrate_db, mysql_create_db
from default.local_default import local_mysql, local_mysql_user

create_db_cmd = mysql_create_db(local_mysql_user, local_mysql.db_name)
dump_cmd = mysql_migrate_db(local_mysql.db_bak_name, local_mysql_user, local_mysql.db_name, local_mysql_user)

if __name__ == '__main__':
    all_cmd = create_db_cmd + "&&\n" + dump_cmd
    print(all_cmd)
    os.system(all_cmd.replace("\n", ""))
