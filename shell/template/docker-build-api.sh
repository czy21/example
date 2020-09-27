#!/bin/bash

# build erp image
cd&&sudo docker build --build-arg JAR_FILE=erp/___output/api/api.jar --tag erp:1.0.0 --file erp/___output/tmp/Dockerfile .