#!/bin/bash --login

cd $(cd "$(dirname "$0")"; pwd)
sh_name="$(basename ${0%.*})"

env="local"

python3 -B ../../script/run.py \
--env ../../shell/${env}/_env.py \
--log-file ${sh_name}.log \
--cmd '
from domain.source import mysql as mysql_source
mysql_source.backup_mysql()
' \
$@