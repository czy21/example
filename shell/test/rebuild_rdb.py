# !/usr/bin/env python

import sys

sys.path.append("../../")
from shell.local import _env

from script.domain.db_source import mysql as mysql_source

mysql_source.rebuild_mysql()
