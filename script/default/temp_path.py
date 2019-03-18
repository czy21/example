# !/usr/bin/env python
import os

root_path = os.path.abspath('../../')

temp_path = root_path + "\\_temp"
temp_db_path = temp_path + "\\db"
temp_db_bak_path = temp_path + "\\db_bak"
temp_api_path = temp_path + "\\api"
temp_web_path = temp_path + "\\web"
temp_operation_path = temp_web_path + "\\operation"

if not os.path.exists(temp_db_path):
    os.makedirs(temp_db_path)
if not os.path.exists(temp_web_path):
    os.makedirs(temp_web_path)
if not os.path.exists(temp_operation_path):
    os.makedirs(temp_operation_path)
if not os.path.exists(temp_api_path):
    os.makedirs(temp_api_path)
if not os.path.exists(temp_db_bak_path):
    os.makedirs(temp_db_bak_path)
