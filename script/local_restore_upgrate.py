# !/usr/bin/env python
import os
import configparser

root_path = os.path.abspath('..')
db_path = root_path+'\\db'
version_path = db_path+'\\create\\200_ByVersion'
scriptPath = os.path.abspath('.')
configPath = scriptPath + '\\config'
os.chdir(configPath)
cf = configparser.ConfigParser()
cf.read("mysql.conf")
db_host = cf.get("db", "db_host")
db_port = cf.get("db", "db_port")
db_user = cf.get("db", "db_user")
db_pass = cf.get("db", "db_pass")
db_name = cf.get("db", "db_name")
db_bak_name = cf.get("db", "db_bak_name")
# dumpcmd = "mysqldump " + db_bak_name + " -u " + db_user + " -p" + db_pass + " --add-drop-table "+  "| "+ "mysql "+ db_name+" -u " + db_user + " -p" + db_pass
# os.system(dumpcmd)


sourcecmd = ""
# os.system(upgradecmd)
for r, s, fs in os.walk(version_path):
    if r[-3:] == "(s)":
        for r, s, fs in os.walk(r):
            for f in fs:
                os.chdir(r)
                sourcecmd += f+","

                # os.system(sourcecmd)
                # os.system(upgradecmd)
upgradecmd = "mysql " + db_name+" -u " + db_user + " -p" + db_pass +" < " + sourcecmd
os.system(upgradecmd)
print(sourcecmd)
