#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)

env="local"

python3 ../../script/run.py \
--env ../../shell/${env}/_env.py \
--init \
--log-file build_api.log \
--param param_project_name=erp-file-resolve param_api_image=erp-file-resolve:play param_api_module_name=file-resolve \
--cmd '
from script.domain.source import base as base_source,java as java_source
java_source.build_api()
base_source.build_template_dict()

'