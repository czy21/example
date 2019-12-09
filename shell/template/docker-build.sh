#!/usr/bin/env bash

# remove all for erp image
sudo docker images | grep erp | awk '{print $3}' | xargs sudo docker rmi -f

# build erp image
cd&&sudo docker build --tag erp:1.0.0 --file ___output/tmp/Dockerfile --build-arg apiPath=___output/api/ .

sudo docker images | grep erp | awk '{print $1":"$2}'