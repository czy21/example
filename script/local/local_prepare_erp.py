#!/usr/bin/python3
import os
from default.path_default import prepare_path, temp_db_path
from default.local_default import local

prepare_sql = open(temp_db_path + "\\prepare.sql", "w+", encoding="utf8")
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
upgradecmd = "mysql" + " -h" + local.db_host + " -P" + local.db_port + " -D" + local.db_name + " -u" + local.db_user + " -p" + local.db_pass + " < " + prepare_sql.name
os.system(upgradecmd)
