#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)

env="local"
source_mount="/volume1/docker-data/volumes/erp_play_portal"

python3 ../../script/run.py \
--env ../../shell/${env}/_env.py \
--init \
--log-file build_api.log \
--param param_api_image=erp_portal:local \
        param_api_module_name=portal \
        param_api_config_path=${source_mount}/config \
        param_api_data_path=${source_mount}/data \
        param_api_log_path=${source_mount}/log \
--cmd '
from script.domain.source import base as base_source,java as java_source
java_source.build_api()
base_source.build_template_dict()

'