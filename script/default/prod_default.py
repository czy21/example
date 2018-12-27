# !/usr/bin/env python
from default.basic_config import db_config, user_param

prod = db_config("prod")
prod_user = user_param(prod.db_port, prod.db_user, prod.db_pass)
