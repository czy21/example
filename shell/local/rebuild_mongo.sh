#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)
sh_name="$(basename ${0%.*})"

env="local"

python3 ../../script/run.py \
--env ../../shell/${env}/_env.py \
--init \
--log-file ${sh_name}.log \
--cmd '
from script.domain.source import mongo as mongo_source
mongo_source.assemble()
#mongo_source.recreate()
mongo_source.exec()
'