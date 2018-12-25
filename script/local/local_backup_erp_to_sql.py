#!/usr/bin/env python3
import os
from default.local_default import local
from default.path_default import backup_path

dump_cmd = "mysqldump"" --skip-add-locks --quick -t " + local.db_name + " -u" + local.db_user + " -p" + local.db_pass + " > " + backup_path + "\\" + local.db_name + "_bak" ".sql"
os.system(dump_cmd)
