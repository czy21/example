# !/usr/bin/env python

import os
import sys

sys.path.append("..")
from prod import prod_backup_db_to_bak, prod_restore_db, prod_upgrade_db


def run(version):
    prod_backup_db_to_bak.run()
    prod_restore_db.run()
    prod_upgrade_db.run(version)


if __name__ == '__main__':
    db_version = input("please input db version: ")
    run(db_version)
    os.system("pause")
