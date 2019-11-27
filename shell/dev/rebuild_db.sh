#!/bin/bash

import="import sys;sys.path.append(\"../../\");from shell.dev import _env;"
rebuild_db="from script.domain import db_source;db_source.rebuild_mysql()"

python3 -c "$import${rebuild_db}"
read -n 1