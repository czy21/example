# !/usr/bin/env python
import os
import tempfile
from common import prepare_path, _temp_db_path, db_host, db_name, db_user, db_pass, db_port

temp_sql = tempfile.NamedTemporaryFile('w+', suffix=".sql", dir=_temp_db_path, delete=False, encoding="utf-8")
for r, s, fs in os.walk(prepare_path):
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
upgradecmd = "mysql" + " -h" + db_host + " -P" + db_port + " -D" + db_name + " -u" + db_user + " -p" + db_pass + " < " + temp_sql.name
os.system(upgradecmd)
