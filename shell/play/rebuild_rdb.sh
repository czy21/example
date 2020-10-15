#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)
python3 ./rebuild_rdb.py $@