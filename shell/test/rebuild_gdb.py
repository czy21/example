# !/usr/bin/env python

import sys

sys.path.append("../../")
from shell.local import _env

from script.domain.db_source import neo4j as neo4j_source

neo4j_source.rebuild_neo4j()
