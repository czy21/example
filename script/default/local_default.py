# !/usr/bin/env python
from default.basic_config import mysql_config, mysql_handler

local_mysql = mysql_config("local_mysql")
local_mysql_user_param = mysql_handler(t_host=local_mysql.db_host, t_port=local_mysql.db_port, t_user=local_mysql.db_user, t_pwd=local_mysql.db_pass).t_user_param
