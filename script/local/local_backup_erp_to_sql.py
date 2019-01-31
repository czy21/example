# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.local_default import local, local_user
from default.path_default import backup_path
from default.basic_config import mysqldump_cmd

dump_cmd = mysqldump_cmd(local.db_name, local_user) + "--skip-add-locks --quick -t " + " > " + backup_path + "\\" + local.db_name + "_bak" ".sql"
os.system(dump_cmd)
