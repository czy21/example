#!/usr/bin/env python3

import sys

sys.path.append("../../")
from shell.local import _env
from script.domain.source import java as java_source

java_source.build_api()
