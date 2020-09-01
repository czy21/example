#!/bin/bash

import="import sys;sys.path.append(\"../../\");from shell.local import _env;"
rebuild_db="from script.domain.db_source import mysql as mysql_source;mysql_source.rebuild_mysql()"

python3 -c "$import${rebuild_db}"
read -n 1