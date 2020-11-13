#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)

env="local"

python3 ../../shell/run.py \
--env ../../shell/${env}/_env.py \
--log-file build_api.log --skip-rm-output \
--cmd '
from script.domain.source import mysql as mysql_source
mysql_source.recover_mysql_gz()
'