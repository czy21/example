#!/bin/bash

import="import sys;sys.path.append(\"../../\");from shell.local import _env;"
rebuild_db="from script.domain.source import java as java_source;java_source.build_api()"

python3 -c "$import${rebuild_db}"
read -n 1