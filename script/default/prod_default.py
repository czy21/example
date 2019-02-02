# !/usr/bin/env python
from default.basic_config import mysql_db_config, mysql_user_param

prod_mysql = mysql_db_config("prod_mysql")
prod_mysql_user = mysql_user_param(prod_mysql.db_port, prod_mysql.db_user, prod_mysql.db_pass)
