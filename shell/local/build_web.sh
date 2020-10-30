#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)

env="local"

python3 ../../shell/run.py \
--env ../../shell/${env}/_env.py \
--log-file build_web.log \
--cmd '
from script.domain.source import web as web_source
#web_source.build_web()
web_source.build_cp_shell()
web_source.build_nginx()
'