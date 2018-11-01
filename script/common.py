# !/usr/bin/env python
import os
import configparser

root_path = os.path.abspath('..')
_temp_path = root_path + "\\_temp"
backup_path = root_path + '\\backup'
_temp_db_path = _temp_path + "\\db"
db_path = root_path + '\\db'
script_path = root_path + "\\script"
config_path = script_path + '\\config'
prepare_path = db_path + '\\create\\100_Prepare'
version_path = db_path + '\\create\\200_ByVersion'

cf = configparser.ConfigParser()
cf.read(config_path + "\\mysql.conf")
db_host = cf.get("db", "db_host")
db_port = cf.get("db", "db_port")
db_user = cf.get("db", "db_user")
db_pass = cf.get("db", "db_pass")
db_name = cf.get("db", "db_name")
db_bak_name = cf.get("db", "db_bak_name")

if not os.path.exists(_temp_db_path):
    os.makedirs(_temp_db_path)
if not os.path.exists(backup_path):
    os.makedirs(backup_path)
