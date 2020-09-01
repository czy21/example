#!/bin/bash

import="import sys;sys.path.append(\"../../\");from shell.local import _env;"
rebuild_db="from script.domain.source import mysql as mysql_source;mysql_source.rebuild_mysql()"

py -c "$import${rebuild_db}"
read -n 1