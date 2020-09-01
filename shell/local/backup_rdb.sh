#!/bin/bash

import="import sys;sys.path.append(\"../../\");from shell.local import _env;"
backup_db="from script.domain.source import mysql as mysql_source;mysql_source.backup_mysql()"

py -c "$import${backup_db}"
read -n 1