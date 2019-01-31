# !/usr/bin/env python
import sys

sys.path.append("..")
from local import local_build_api, local_build_op

local_build_api.build_to_temp()
local_build_op.build_to_temp()