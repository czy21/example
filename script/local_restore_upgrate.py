# !/usr/bin/env python
import os
from common import version_path, temp_db_path, db_name, db_user, db_pass, db_bak_name

dumpcmd = "mysqldump " + db_bak_name + " -u " + db_user + " -p" + db_pass + " --add-drop-table " + "| " + "mysql " + db_name + " -u " + db_user + " -p" + db_pass
os.system(dumpcmd)
all_sql = open(temp_db_path + "\\prepare.sql", "w+", encoding="utf8")
for r, s, fs in os.walk(version_path):
    if r[-3:] == "(s)":
        for r, s, fs in os.walk(r):
            for f in fs:
                structure_sql = open(r + "\\" + f, 'r', encoding="utf-8")
                all_sql.write(structure_sql.read() + "\n")
                structure_sql.close()
    elif r[-3:] == "(i)":
        for r, s, fs in os.walk(r):
            for f in fs:
                init_data_sql = open(r + "\\" + f, 'r', encoding="utf-8")
                all_sql.write(init_data_sql.read())
                init_data_sql.close()
all_sql.close()
upgradecmd = "mysql " + db_name + " -u " + db_user + " -p" + db_pass + " < " + all_sql.name
os.system(upgradecmd)
