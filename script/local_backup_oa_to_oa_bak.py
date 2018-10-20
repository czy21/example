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
db_port = cf.get("db", "db_port")
db_user = cf.get("db", "db_user")
db_pass = cf.get("db", "db_pass")
db_name = cf.get("db", "db_name")
db_bak_name = cf.get("db", "db_bak_name")
dumpcmd = "mysqldump " + db_name + " -u " + db_user + " -p" + db_pass + " --add-drop-table "+  "| "+ "mysql "+ db_bak_name+" -u " + db_user + " -p" + db_pass
os.system(dumpcmd)
