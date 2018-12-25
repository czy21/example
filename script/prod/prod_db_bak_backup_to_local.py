#!/usr/bin/env python3
import os
from default.local_default import local
from default.prod_default import prod

dump_cmd = "mysqldump" + " -P" + prod.db_port + " " + prod.db_bak_name + " -u" + prod.db_user + " -p" + prod.db_pass + " --add-drop-table " + "| " + "mysql" + " -P" + local.db_port + " " + local.db_bak_name + " -u" + local.db_user + " -p" + local.db_pass
os.system(dump_cmd)
