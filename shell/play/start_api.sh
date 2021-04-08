#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)

env="play"

python3 ../../script/run.py \
--env ../../shell/${env}/_env.py \
--init \
--log-file start_api.log \
--param param_api_image=purge:play param_api_compose_scale_worker=2 \
--cmd '
from script.domain.source import base as base_source,java as java_source;
java_source.build_api()
base_source.build_template_dict()
base_source.down_container()
base_source.start_api_compose()
'