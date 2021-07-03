#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)

env="local"

python3 ../../script/run.py \
--env ../../shell/${env}/_env.py \
--log-file backup_cp_rdb.log \
--cmd '
from script.domain.source import mysql as mysql_source
mysql_source.backup_mysql()
'