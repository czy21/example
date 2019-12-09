# !/usr/bin/env python

import sys;

sys.path.append("../../");
from shell.local import _env;
from script.domain import api_source;

api_source.build_api()