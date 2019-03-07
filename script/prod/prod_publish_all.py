# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from prod import prod_publish_db
from local import local_build_api, local_build_all
from prod import prod_publish_api, prod_publish_op

api_version = input("please input api version: ")
db_version = input("please input db version: ")
local_build_api.update_version(api_version)
local_build_all.to_temp()
prod_publish_db.run(db_version)
prod_publish_op.run()
prod_publish_api.run()
os.system("pause")
