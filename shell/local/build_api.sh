#!/bin/bash

import="import sys;sys.path.append(\"../../\");from shell.local import _env;"
rebuild_db="from script.domain import api_source;api_source.build_api()"

python3 -c "$import${rebuild_db}"
read -n 1