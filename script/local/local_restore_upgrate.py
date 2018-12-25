#!/usr/bin/env python3
import os
from default.path_default import temp_db_path, version_path
from default.local_default import local

local_user = " -u" + local.db_user + " -p" + local.db_pass
local_port = " -P" + local.db_port + " "
dumpcmd = "mysqldump" + local_port + local.db_bak_name + local_user + " --add-drop-table " + "| " + "mysql" + local_port + local.db_name + local_user
#
# "mysqldump" + " -P" + prod.db_port + " " + prod.db_bak_name + " -u" + prod.db_user + " -p" + prod.db_pass + " --add-drop-table " + "| " + "mysql" + " -P" + local.db_port + " " + local.db_bak_name + " -u" + local.db_user + " -p" + local.db_pass
print(dumpcmd)
print(local_user)
# os.system(dumpcmd)
# all_sql = open(temp_db_path + "\\all_in_one.sql", "w+", encoding="utf8")
# for r, s, fs in os.walk(version_path):
#     if r[-3:] == "(s)":
#         for r, s, fs in os.walk(r):
#             for f in fs:
#                 structure_sql = open(r + "\\" + f, 'r', encoding="utf-8")
#                 all_sql.write(structure_sql.read() + "\n")
#                 structure_sql.close()
#     elif r[-3:] == "(i)":
#         for r, s, fs in os.walk(r):
#             for f in fs:
#                 init_data_sql = open(r + "\\" + f, 'r', encoding="utf-8")
#                 all_sql.write(init_data_sql.read())
#                 init_data_sql.close()
# all_sql.close()
# print(all_sql)
# upgradecmd = "mysql " + db_name + " -u " + db_user + " -p" + db_pass + " < " + all_sql.name
# os.system(upgradecmd)
