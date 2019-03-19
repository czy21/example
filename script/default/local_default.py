# !/usr/bin/env python
from default.basic_config import mysql_db_config, mysql

local_mysql = mysql_db_config("local_mysql")
local_mysql_user_param = mysql(t_host=local_mysql.db_host, t_port=local_mysql.db_port, t_user=local_mysql.db_user, t_pwd=local_mysql.db_pass).t_user_param
