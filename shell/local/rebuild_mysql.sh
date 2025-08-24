#!/bin/bash

dir=$(cd "$(dirname "$0")"; pwd)
source ${dir}/../env_common.sh

python3 -B ${run_py} --file ${sh_file} --env-file ${dir}/../_env.yml ${dir}/_env.yml \
--exec '
from domain.source.mysql import MySQLSource

db_source=MySQLSource(context)
db_source.assemble()
db_source.recreate()
db_source.execute()
' \
$@