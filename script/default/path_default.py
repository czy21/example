# !/usr/bin/env python
import os
import shutil

root_path = os.path.abspath('../../')

code_path = root_path + '\\code'
api_path = code_path + '\\api'
web_path = code_path + '\\web'
operation_path = web_path + '\\operation'
backup_path = root_path + '\\backup'
script_path = root_path + "\\script"
db_path = root_path + '\\db'
prepare_path = db_path + '\\create\\100_Prepare'
version_path = db_path + '\\create\\200_ByVersion'
temp_path = root_path + "\\_temp"
temp_db_path = temp_path + "\\db"
temp_api_path = temp_path + "\\api"
temp_web_path = temp_path + "\\web"
temp_operation_path = temp_web_path + "\\operation"

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
