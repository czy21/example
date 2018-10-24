# !/usr/bin/env python
import os
from common import db_name, db_user, db_pass,backup_path
dumpcmd = "mysqldump"" --skip-add-locks --quick -t " + db_name + " -u" + db_user + " -p" + db_pass + " > " + backup_path + "\\" + db_name + "_bak" ".sql"
os.system(dumpcmd)
