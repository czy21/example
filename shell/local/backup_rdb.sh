#!/bin/bash

import="import sys;sys.path.append(\"../../\");from shell.local import _env;"
backup_db="from script.domain import db_source;db_source.backup_mysql()"

python3 -c "$import${backup_db}"
read -n 1