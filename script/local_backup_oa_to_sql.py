# !/usr/bin/env python
import os
import configparser

scriptPath = os.path.abspath('.')
backupPath = scriptPath + '\\backup'
configPath = scriptPath + '\\config'
os.chdir(configPath)
cf = configparser.ConfigParser()
cf.read("mysql.conf")
db_host = cf.get("db", "db_host")
db_port = cf.getint("db", "db_port")
db_user = cf.get("db", "db_user")
db_pass = cf.get("db", "db_pass")
db_name = cf.get("db", "db_name")
dumpcmd = "mysqldump"" --skip-add-locks --quick -t " + db_name + " -u" + db_user + " -p" + db_pass + " > " + backupPath + "\\" + db_name + "_bak" ".sql"
os.system(dumpcmd)
