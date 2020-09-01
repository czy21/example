#!/bin/bash

import="import sys;sys.path.append(\"../../\");from shell.local import _env;"
rebuild_db="from script.domain.db_source import neo4j as neo4j_source;neo4j_source.rebuild_neo4j()"

python3 -c "$import${rebuild_db}"
read -n 1
