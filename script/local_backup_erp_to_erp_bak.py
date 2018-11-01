# !/usr/bin/env python
import os
from common import db_name, db_bak_name, db_user, db_pass

dumpcmd = "mysqldump " + db_name + " -u " + db_user + " -p" + db_pass + " --add-drop-table " + "| " + "mysql " + db_bak_name + " -u " + db_user + " -p" + db_pass
os.system(dumpcmd)
