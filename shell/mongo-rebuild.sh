#!/bin/bash

dir=$(cd "$(dirname "$0")"; pwd)
source ${dir}/_common.sh

$CLI --exec '
from domain.source.mongo import MongoSource

db_source=MongoSource(context)
db_source.assemble()
db_source.recreate()
db_source.execute()
' \
$@