#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)

env="local"

python3 ../../script/run.py \
--env ../../shell/${env}/_env.py \
--init \
--log-file build_api_file_resolve.log \
--param param_api_image=erp_file_resolve:local \
        param_api_module_name=file_resolve \
        param_api_source_share_dir=portal/data \
        param_api_dest_share_dir=data
--cmd '
from script.domain.source import base as base_source,java as java_source
java_source.build_api()
base_source.build_template_dict()
base_source.down_container();
base_source.start_api_compose()

'