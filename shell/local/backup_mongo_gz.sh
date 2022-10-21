#!/bin/bash --login

dir=$(cd "$(dirname "$0")"; pwd)
source ${dir}/../env_common.sh

python3 -B ${run_py} --env ${env_py} \
--log-file ${sh_name}.log \
--cmd '
from domain.source import mongo as db_source
db_source.backup_gz()
' \
$@