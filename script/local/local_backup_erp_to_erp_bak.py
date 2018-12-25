#!/usr/bin/env python3
import os
from default.local_default import local

dump_cmd = "mysqldump" + " -P" + local.db_port + " " + local.db_name + " -u" + local.db_user + " -p" + local.db_pass + " --add-drop-table " + "| " + "mysql" + " -P" + local.db_port + " " + local.db_bak_name + " -u" + local.db_user + " -p" + local.db_pass
os.system(dump_cmd)
