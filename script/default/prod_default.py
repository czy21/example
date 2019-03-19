# !/usr/bin/env python
from default.basic_config import mysql_db_config, mysql

prod_mysql = mysql_db_config("prod_mysql")
prod_mysql_user_param = mysql(t_host=prod_mysql.db_host, t_port=prod_mysql.db_port, t_user=prod_mysql.db_user, t_pwd=prod_mysql.db_pass).t_user_param
