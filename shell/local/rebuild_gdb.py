#!/usr/bin/env python3
import sys
from pathlib import Path

sys.path.append(Path(__file__).joinpath("../../../").resolve().as_posix())
from shell import run

run.exec_file({"script.domain.source.neo4j": ["rebuild_neo4j"]})
