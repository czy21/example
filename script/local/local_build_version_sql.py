# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from default.path_default import version_path
from default.temp_path import temp_db_path


def built_version_sql(version_dir_val):
    all_sql = open(temp_db_path + "\\all_in_one.sql", "w+", encoding="utf8")
    for r, s, fs in os.walk(version_path + "\\" + version_dir_val):
        if r[-3:] == "(s)":
            for r, s, fs in os.walk(r):
                for f in fs:
                    structure_sql = open(r + "\\" + f, 'r', encoding="utf-8")
                    all_sql.write(structure_sql.read() + "\n")
                    structure_sql.close()
        if r[-3:] == "(i)":
            for r, s, fs in os.walk(r):
                for f in fs:
                    init_data_sql = open(r + "\\" + f, 'r', encoding="utf-8")
                    all_sql.write(init_data_sql.read())
                    init_data_sql.close()
    all_sql.close()
    return all_sql.name
