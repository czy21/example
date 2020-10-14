#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)
python3 ./build_api_compose.py $@