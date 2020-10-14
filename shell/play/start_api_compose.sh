#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)
python3 ./start_api_compose.py $@