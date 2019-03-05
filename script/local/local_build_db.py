# !/usr/bin/env python
import os, sys, re

sys.path.append("..")
from default.path_default import db_version_path, db_everyRun_path
from default.temp_path import temp_db_path


# max_target_table_id{{table_id,table_name}};
def parse_max_target_table_id(source_sql_str):
    match_result = re.findall(r"max_target_table_id{{(.+?)}}", source_sql_str, re.M)
    if match_result.__len__() == 0:
        return source_sql_str
    for target in match_result:
        max_sql = "(select ifnull((select max(" + target.split(",")[0] + ")from " + target.split(",")[1] + ")" \
                  + ",(select wn.sequence_value from worker_node wn order by wn.modified_time desc limit 1)))"
        return re.sub(r"max_target_table_id{{" + target.split(",")[0] + "," + target.split(",")[1] + "}}", max_sql, source_sql_str)


def built_version_sql(version_dir_val):
    all_sql = open(temp_db_path + "\\all_in_one.sql", "w+", encoding="utf8")
    for r, ds, fs in os.walk(db_version_path + "\\" + version_dir_val):
        if r[-3:] == "(s)":
            for tr, tds, tfs in os.walk(r):
                for f in tfs:
                    structure_sql = open(tr + "\\" + f, 'r', encoding="utf-8")
                    all_sql.write(parse_max_target_table_id(structure_sql.read()) + "\n")
                    structure_sql.close()
        if r[-3:] == "(i)":
            for tr, tds, tfs in os.walk(r):
                for f in tfs:
                    init_data_sql = open(tr + "\\" + f, 'r', encoding="utf-8")
                    all_sql.write(parse_max_target_table_id(init_data_sql.read()) + "\n")
                    init_data_sql.close()
    for r, ds, fs in os.walk(db_everyRun_path):
        for f in fs:
            increment_sql = open(r + "\\" + f, 'r', encoding="utf-8")
            all_sql.write(parse_max_target_table_id(increment_sql.read()) + "\n")
            increment_sql.close()
    all_sql.close()
    return all_sql.name


if __name__ == '__main__':
    db_version = input("please input db version: ")
    built_version_sql(db_version + 'v')
    os.system("pause")
