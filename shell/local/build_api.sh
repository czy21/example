#!/bin/bash

env="local"

cmd="'sss'"
echo 'python3 ../../shell/run.py \
--env ../../shell/${env}/_env.py \
--log-file build_plugin.log \
--cmd '${cmd}''