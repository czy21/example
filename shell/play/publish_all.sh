#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)
python3 ./publish_all.py $@