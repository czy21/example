# !/usr/bin/env python
import os
import sys

sys.path.append("..")
from local import local_build_api, local_build_op
from prod import prod_publish_api, prod_publish_op

local_build_api.build_to_temp()
local_build_op.build_to_temp()
prod_publish_api.run()
prod_publish_op.run()
os.system("pause")
