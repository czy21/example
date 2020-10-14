#!/usr/bin/env python3
import sys,os

sys.path.append("../../")
from shell import run

run.exec_file({"script.domain.source.java": ["build_plugin", "build_api", "build_api_image"]})
