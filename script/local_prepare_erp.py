# !/usr/bin/env python
import os
from common import prepare_path, _temp_db_path, db_host, db_name, db_user, db_pass, db_port

prepare_sql = open(_temp_db_path + "\\prepare.sql", "w+", encoding="utf8")
for r, s, fs in os.walk(prepare_path):
    if r[-3:] == "(s)":
        for r, s, fs in os.walk(r):
            for f in fs:
                structure_sql = open(r + "\\" + f, 'r', encoding="utf-8")
                prepare_sql.writelines(structure_sql.read() + "\n")
                structure_sql.close()
    elif r[-3:] == "(i)":
        for r, s, fs in os.walk(r):
            for f in fs:
                init_data_sql = open(r + "\\" + f, 'r', encoding="utf-8")
                prepare_sql.writelines(init_data_sql.read())
                init_data_sql.close()
prepare_sql.close()
upgradecmd = "mysql" + " -h" + db_host + " -P" + db_port + " -D" + db_name + " -u" + db_user + " -p" + db_pass + " < " + prepare_sql.name
os.system(upgradecmd)
