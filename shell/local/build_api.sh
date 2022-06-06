#!/bin/bash

dir=$(cd "$(dirname "$0")"; pwd)
source ${dir}/../env_common.sh

python3 -B ${run_py} --env ${env_py} \
--init \
--log-file ${sh_name}.log \
--param param_api_module_name=portal \
--cmd '
from script.domain.source import base as base_source,java as java_source
java_source.build_api()
' \
$@