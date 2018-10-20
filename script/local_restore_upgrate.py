# !/usr/bin/env python
import os
import configparser
import tempfile

root_path = os.path.abspath('..')
temp_path = root_path + "\\_temp"
db_temp_path = temp_path + "\\db"
db_path = root_path + '\\db'
script_path = root_path + "\\script"
config_path = script_path + '\\config'
version_path = db_path + '\\create\\200_ByVersion'
os.chdir(config_path)
cf = configparser.ConfigParser()
cf.read("mysql.conf")
db_host = cf.get("db", "db_host")
db_port = cf.get("db", "db_port")
db_user = cf.get("db", "db_user")
db_pass = cf.get("db", "db_pass")
db_name = cf.get("db", "db_name")
db_bak_name = cf.get("db", "db_bak_name")
dumpcmd = "mysqldump " + db_bak_name + " -u " + db_user + " -p" + db_pass + " --add-drop-table " + "| " + "mysql " + db_name + " -u " + db_user + " -p" + db_pass
os.system(dumpcmd)
temp_sql = tempfile.NamedTemporaryFile('w+', suffix=".sql", dir=db_temp_path, delete=False, encoding="utf-8")
for r, s, fs in os.walk(version_path):
    if r[-3:] == "(s)":
        for r, s, fs in os.walk(r):
            for f in fs:
                os.chdir(r)
                struc_sql = open(f, 'r', encoding="utf-8")
                temp_sql.write(struc_sql.read())
                struc_sql.close()
    elif r[-3:] == "(i)":
        for r, s, fs in os.walk(r):
            for f in fs:
                os.chdir(r)
                initdata_sql = open(f, 'r', encoding="utf-8")
                temp_sql.write(initdata_sql.read())
                initdata_sql.close()
temp_sql.seek(0)
temp_sql.close()
upgradecmd = "mysql " + db_name + " -u " + db_user + " -p" + db_pass + " < " + temp_sql.name
os.system(upgradecmd)
