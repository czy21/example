#!/bin/bash

set -e

sudo yum install -y yum-utils device-mapper-persistent-data lvm2
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
sudo yum makecache fast
sudo yum -y install docker-ce
sudo sed -i "16iExecStart=\nExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix://var/run/docker.sock" /usr/lib/systemd/system/docker.service
sudo systemctl daemon-reload
sudo systemctl restart docker