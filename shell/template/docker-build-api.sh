#!/bin/bash

# build erp image
cd&&sudo docker build --tag erp:1.0.0 --file ___output/tmp/Dockerfile --build-arg apiPath=___output/api/ .