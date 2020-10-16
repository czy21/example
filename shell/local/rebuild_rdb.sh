#!/bin/bash

env="local"

python3 ../../shell/run.py \
--env ../../shell/${env}/_env.py \
--log-file build_plugin.log \
--cmd '
from script.domain.source import mysql as mysql_source
mysql_source.assemble()
mysql_source.recreate()
mysql_source.exec()
'