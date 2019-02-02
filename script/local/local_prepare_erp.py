# !/usr/bin/env python
import os, sys
sys.path.append("..")
from default.path_default import db_prepare_path
from default.temp_path import temp_db_path
from default.local_default import local_mysql, local_mysql_user
from default.basic_config import mysql_cmd

prepare_sql = open(temp_db_path + "\\prepare.sql", "w+", encoding="utf8")
for r, s, fs in os.walk(db_prepare_path):
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
upgrade_cmd = mysql_cmd(local_mysql.db_name, local_mysql_user) + " < " + prepare_sql.name
os.system(upgrade_cmd)
