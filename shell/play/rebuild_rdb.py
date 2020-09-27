#!/usr/bin/env python3

import sys

sys.path.append("../../")
from shell.play import _env

from script.domain.source import mysql as mysql_source

mysql_source.rebuild_mysql()
