#!/bin/bash

dir=$(cd "$(dirname "$0")"; pwd)
source ${dir}/_common.sh

$CLI --exec '
from domain.source.pgsql import PgSQLSource

db_source=PgSQLSource(context)
db_source.backup()
' \
$@