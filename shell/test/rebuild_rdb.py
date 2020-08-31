# !/usr/bin/env python

import sys

sys.path.append("../../")
from shell.local import _env

from script.domain import db_source

db_source.rebuild_mysql()