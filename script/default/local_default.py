# !/usr/bin/env python
from default.basic_config import db_config, user_param

local = db_config("local")
local_user = user_param(local.db_port, local.db_user, local.db_pass)
