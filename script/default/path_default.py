# !/usr/bin/env python
import os

root_path = os.path.abspath('../../')

code_path = root_path + '\\code'
api_path = code_path + '\\api'
web_path = code_path + '\\web'
operation_path = web_path + '\\operation'
backup_path = root_path + '\\backup'
script_path = root_path + "\\script"
db_path = root_path + '\\db'
db_prepare_path = db_path + '\\create\\100_Prepare'
db_version_path = db_path + '\\create\\200_ByVersion'
db_everyRun_path = db_path + '\\create\\300_EveryRun'


def with_param_api_path(*params):
    return " " + " ".join(params) + " " + api_path
