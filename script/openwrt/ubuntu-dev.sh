#!/usr/bin/env bash

docker run -it -d --name ubuntu_one -p 4033:22 ubuntu:18.04

docker exec -it ubuntu_one /bin/bash -c "
sed -i 's/http:\/\/archive.ubuntu.com/http:\/\/mirrors.aliyun.com/g' /etc/apt/sources.list;
apt-get update;
apt install -y openssh-client openssh-server subversion g++ zlib1g-dev build-essential git python rsync man-db;
apt install -y libncurses5-dev gawk gettext unzip file libssl-dev wget zip time;"
