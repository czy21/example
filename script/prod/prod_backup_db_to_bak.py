# !/usr/bin/env python
import os, sys

sys.path.append("..")

from default.prod_default import prod, prod_user
from default.basic_config import migrate_db_sql

dump_cmd = migrate_db_sql(prod.db_name, prod_user, prod.db_bak_name, prod_user)
os.system(dump_cmd)
