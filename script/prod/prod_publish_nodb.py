# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from local import local_build_all
from prod import prod_publish_api, prod_publish_op

local_build_all.to_temp()
prod_publish_api.run()
prod_publish_op.run()
os.system("pause")
