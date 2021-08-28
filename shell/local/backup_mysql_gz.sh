#!/bin/bash

dir=$(cd "$(dirname "$0")"; pwd)
source ${dir}/../env_common.sh

python3 ${run_py} --env ${env_py} \
--log-file ${sh_name}.log \
--cmd '
from script.domain.source import mysql as db_source
db_source.backup_gz()
'