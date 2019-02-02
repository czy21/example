# !/usr/bin/env python
import os, sys

sys.path.append("..")
from default.basic_config import mysql_migrate_db
from default.prod_default import prod_mysql, prod_mysql_user

dump_cmd = mysql_migrate_db(prod_mysql.db_bak_name, prod_mysql_user, prod_mysql.db_name, prod_mysql_user)
os.system(dump_cmd)
