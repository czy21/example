# !/usr/bin/env python

import os
import sys

sys.path.append("..")
from default import font_color
from default.font_color import printWithColor
from prod import prod_backup_db_to_bak, prod_restore_db, prod_upgrade_db


def run(version):
    prod_backup_db_to_bak.run()
    prod_restore_db.run()

    line = input("execute upgrade db (1.true 2.false default true)")
    if line == "1":
        prod_upgrade_db.run(version, True)
    elif line == "2":
        printWithColor('no execute upgrade db', font_color.GREEN)


if __name__ == '__main__':
    db_version = input("please input db version: ")
    run(db_version)
    os.system("pause")
