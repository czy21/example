#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)
sh_name="$(basename ${0%.*})"

env="local"

python3 ../../script/run.py \
--env ../../shell/${env}/_env.py \
--init \
--log-file ${sh_name}.log \
--param param_api_module_name=portal \
--cmd '
from script.domain.source import base as base_source,java as java_source
java_source.build_api()
'