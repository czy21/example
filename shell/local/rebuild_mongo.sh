#!/bin/bash

dir=$(cd "$(dirname "$0")"; pwd)
source ${dir}/../env_common.sh

python3 ${run_py} --env ${env_py} \
--init \
--log-file ${sh_name}.log \
--cmd '
from script.domain.source import mongo as db_source
db_source.assemble()
db_source.recreate()
db_source.execute()
'