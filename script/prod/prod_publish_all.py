# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from prod.prod_publish_db import db_update
from local import local_build_api, local_build_op
from prod import prod_publish_api, prod_publish_op

api_version = input("please input api version: ")
db_version = input("please input db version: ")
local_build_api.update_api_version(api_version)
local_build_api.build_to_temp()
local_build_op.build_to_temp()
db_update(db_version)
prod_publish_api.run()
prod_publish_op.run()
os.system("pause")
