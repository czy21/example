# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from local import local_restore_db
from local import local_upgrade_db
from default import font_color
from default.font_color import printWithColor

if __name__ == '__main__':
    printWithColor('starting restore db', font_color.GREEN)
    printWithColor(local_restore_db.restore_db_cmd, font_color.DARKSKYBLUE)
    os.system(local_restore_db.restore_db_cmd)
    printWithColor('finished restore db', font_color.GREEN)
    if local_upgrade_db.build_upgrade_cmd() is not None:
        printWithColor('starting upgrade db', font_color.GREEN)
        printWithColor(local_upgrade_db.build_upgrade_cmd(), font_color.DARKSKYBLUE)
        os.system(local_upgrade_db.build_upgrade_cmd())
        printWithColor('finished upgrade db', font_color.GREEN)
    os.system("pause")
