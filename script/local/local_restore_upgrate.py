# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from local import local_restore_db
from local import local_upgrade_db
from default import font_color
from default.font_color import printWithColor

if __name__ == '__main__':
    printWithColor('db restoring', font_color.GREEN)
    printWithColor(local_restore_db.restore_db_cmd, font_color.DARKSKYBLUE)
    os.system(local_restore_db.restore_db_cmd)
    printWithColor('db restored', font_color.GREEN)
    if local_upgrade_db.build_upgrade_cmd(__file__) is not None:
        printWithColor('db upgrading', font_color.GREEN)
        printWithColor(local_upgrade_db.build_upgrade_cmd(__file__), font_color.DARKSKYBLUE)
        os.system(local_upgrade_db.build_upgrade_cmd(__file__))
        printWithColor('db upgraded', font_color.GREEN)
    os.system("pause")
