#!/usr/bin/env python3

import sys

sys.path.append("../../")
from shell.local import _env

from script.domain.source import neo4j as neo4j_source

neo4j_source.rebuild_neo4j()
