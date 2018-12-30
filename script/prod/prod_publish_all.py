# !/usr/bin/env python
import os, sys

sys.path.append("..")
from prod.prod_publish_db import db_update
from local import local_build_api
from prod import prod_publish_api, prod_publish_op

api_version = input("please input api version: ")
db_version = input("please input db version: ")
local_build_api.update_version(api_version)
prod_publish_api.run()
prod_publish_op.run()
db_update(db_version)
os.system("pause")
