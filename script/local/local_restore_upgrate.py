# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from local import local_restore_db
from local import local_upgrade_db

if __name__ == '__main__':
    local_restore_db.run()
    local_upgrade_db.run()
    os.system("pause")
