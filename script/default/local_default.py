# !/usr/bin/env python
from default.basic_config import mysql_db_config, mysql_user_param, mongo_db_config

local_mysql = mysql_db_config("local_mysql")
local_mysql_user = mysql_user_param(local_mysql.db_port, local_mysql.db_user, local_mysql.db_pass)
