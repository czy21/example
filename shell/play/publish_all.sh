#!/bin/bash

python3 -c '

#!/usr/bin/env python3
import sys

sys.path.append("../../")
from shell import run

run.exec_file({
"script.domain.source.mysql": ["rebuild_mysql"],
"script.domain.source.java": ["build_plugin", "build_api", "build_api_image","start_api_compose"]
})
'

$@