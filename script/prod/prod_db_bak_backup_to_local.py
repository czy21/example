#!/usr/bin/env python3
import os

from default.basic_config import migrate_db_sql
from default.local_default import local, local_user
from default.prod_default import prod, prod_user

dump_cmd = migrate_db_sql(prod.db_bak_name, prod_user, local.db_bak_name, local_user)
os.system(dump_cmd)
