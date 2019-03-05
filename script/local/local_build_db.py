# !/usr/bin/env python
import os, sys

sys.path.append("..")
from default.path_default import db_version_path, db_everyRun_path
from default.temp_path import temp_db_path


def built_version_sql(version_dir_val):
    all_sql = open(temp_db_path + "\\all_in_one.sql", "w+", encoding="utf8")
    for r, ds, fs in os.walk(db_version_path + "\\" + version_dir_val):
        if r[-3:] == "(s)":
            for r, ds, fs in os.walk(r):
                for f in fs:
                    structure_sql = open(r + "\\" + f, 'r', encoding="utf-8")
                    all_sql.write(structure_sql.read() + "\n")
                    structure_sql.close()
        if r[-3:] == "(i)":
            for r, ds, fs in os.walk(r):
                for f in fs:
                    init_data_sql = open(r + "\\" + f, 'r', encoding="utf-8")
                    all_sql.write(init_data_sql.read())
                    init_data_sql.close()
    for r, ds, fs in os.walk(db_everyRun_path):
        for f in fs:
            increment_sql = open(r + "\\" + f, 'r', encoding="utf-8")
            all_sql.write(increment_sql.read() + "\n")
            increment_sql.close()
    all_sql.close()
    return all_sql.name


if __name__ == '__main__':
    db_version = input("please input db version: ")
    built_version_sql(db_version + 'v')
    os.system("pause")
