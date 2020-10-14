#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)
pip3 install -r ../../script/requirements.txt
python3 ./rebuild_gdb.py $@