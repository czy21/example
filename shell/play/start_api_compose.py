#!/usr/bin/env python3
import sys

sys.path.append("../../")
from shell import run

run.exec_file({"script.domain.source.java": ["ensure_network", "start_api_compose"]})
