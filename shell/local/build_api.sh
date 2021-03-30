#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)

env="local"

python3 ../../script/run.py \
--env ../../shell/${env}/_env.py \
--log-file build_api.log \
--init \
--cmd '
from script.domain.source import base as base_source,java as java_source
java_source.build_api()
base_source.build_template_dict()

'