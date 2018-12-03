# !/usr/bin/env python
import os
import shutil
import configparser

root_path = os.path.abspath('..')
code_path = root_path + '\\code'
api_path = code_path + '\\api'
web_path = code_path + '\\web'
operation_path = web_path + '\\operation'
backup_path = root_path + '\\backup'
script_path = root_path + "\\script"
config_path = script_path + '\\config'
db_path = root_path + '\\db'
prepare_path = db_path + '\\create\\100_Prepare'
version_path = db_path + '\\create\\200_ByVersion'
temp_path = root_path + "\\_temp"
temp_db_path = temp_path + "\\db"
temp_api_path = temp_path + "\\api"
temp_web_path = temp_path + "\\web"
temp_operation_path = temp_web_path + "\\operation"

cf = configparser.ConfigParser()
cf.read(config_path + "\\mysql.conf")
cf.read(config_path + "\\shell.conf")
cf.read(config_path + "\\remote.conf")
erp_home = cf.get("home", "erp_home")
api_build_shell = cf.get("api", "api_build_shell")
web_build_shell = cf.get("web", "web_build_shell")
db_host = cf.get("db", "db_host")
db_port = cf.get("db", "db_port")
db_user = cf.get("db", "db_user")
db_pass = cf.get("db", "db_pass")
db_name = cf.get("db", "db_name")
db_bak_name = cf.get("db", "db_bak_name")

if not os.path.exists(backup_path):
    os.makedirs(backup_path)
if not os.path.exists(temp_db_path):
    os.makedirs(temp_db_path)
if not os.path.exists(temp_web_path):
    os.makedirs(temp_web_path)
if not os.path.exists(temp_operation_path):
    os.makedirs(temp_operation_path)
if not os.path.exists(temp_api_path):
    os.makedirs(temp_api_path)
else:
    shutil.rmtree(temp_api_path)
    os.makedirs(temp_api_path)
