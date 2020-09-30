#!/usr/bin/env python3
import sys

sys.path.append("../../")
from shell import run

run.exec_file({"script.domain.source.neo4j": ["rebuild_neo4j"]})
