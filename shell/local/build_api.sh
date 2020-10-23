#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)

env="local"

python3 ../../shell/run.py \
--env ../../shell/${env}/_env.py \
--log-file build_api.log \
--cmd '
from script.domain.source import java as java_source
java_source.build_plugin()
java_source.build_api()
'