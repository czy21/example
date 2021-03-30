#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)

env="test"

python3 ../../script/run.py \
--env ../../shell/${env}/_env.py \
--log-file start_api.log \
--cmd '
from script.domain.source import java as java_source
java_source.start_api_compose()
'