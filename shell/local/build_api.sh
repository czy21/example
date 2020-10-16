#!/bin/bash

env="local"


python3 ../../shell/run.py \
--env ../../shell/${env}/_env.py \
--log-file build_plugin.log \
--cmd '
from script.domain.source import java as java_source
java_source.build_api()
'