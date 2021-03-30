#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)

env="test"

python3 ../../script/run.py \
--env ../../shell/${env}/_env.py \
--log-file build_api.log \
--param param_api_image=erp:test \
--cmd '
from script.domain.source import java as java_source
#java_source.ensure_network()
#java_source.build_api()
java_source.build_api_compose()
java_source.start_api_compose()
'