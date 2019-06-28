# !/usr/bin/env python
import os
import re
import sys

sys.path.append("..")
from default.path_default import db_version_path, db_everyRun_path
from default.temp_path import temp_db_path


# max_target_table_id{{table_id,table_name}};
def parse_max_target_table_id(source_sql_str):
    all_lines = []
    for cur_line in source_sql_str:
        match_result = ''.join(re.findall(r"max_target_table_id{{(.+?)}}", cur_line, re.M))
        if match_result != '':
            max_sql = "(select ifnull((select max(" + match_result.split(",")[0] + ")from " + match_result.split(",")[1] + ")" \
                      + ",(select wn.sequence_value from sys_work_node wn order by wn.modified_time desc limit 1)))"
            all_lines.append(re.sub(r"max_target_table_id{{" + match_result.split(",")[0] + "," + match_result.split(",")[1] + "}}", max_sql, cur_line))
        else:
            all_lines.append(cur_line)

    return ''.join(all_lines)


def built_version_sql(version_dir_val):
    all_sql = open(temp_db_path + "\\all_in_one.sql", "w+", encoding="utf8")
    for r, ds, fs in os.walk(db_version_path + "\\" + version_dir_val):
        if r[-3:] == "(s)":
            for tr, tds, tfs in os.walk(r):
                for f in tfs:
                    structure_sql = open(tr + "\\" + f, 'r', encoding="utf-8")
                    all_sql.write(parse_max_target_table_id(structure_sql.readlines()) + "\n")
                    structure_sql.close()
        if r[-3:] == "(i)":
            for tr, tds, tfs in os.walk(r):
                for f in tfs:
                    init_data_sql = open(tr + "\\" + f, 'r', encoding="utf-8")
                    all_sql.write(parse_max_target_table_id(init_data_sql.readlines()) + "\n")
                    init_data_sql.close()
    for r, ds, fs in os.walk(db_everyRun_path):
        for f in fs:
            increment_sql = open(r + "\\" + f, 'r', encoding="utf-8")
            all_sql.write(parse_max_target_table_id(increment_sql.readlines()) + "\n")
            increment_sql.close()
    all_sql.close()
    return all_sql.name


if __name__ == '__main__':
    db_version = input("please input db version: ") + 'v'
    built_version_sql(db_version)
    os.system("pause")
