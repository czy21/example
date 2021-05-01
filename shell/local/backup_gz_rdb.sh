#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)

env="local"

python3 ../../script/run.py \
--env ../../shell/${env}/_env.py \
--init \
--log-file backup_gz_rdb.log \
--cmd '
from script.domain.source import mysql as mysql_source
mysql_source.backup_mysql_gz()
'