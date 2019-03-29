#!/bin/bash

set -e

# sudo yum -y install wget

# ali source
# if [! -f "/etc/yum.repos.d/CentOS-Base.repo_bak"];then
#     sudo mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo_bak
# fi
# sudo wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
# sudo yum clean all
# sudo yum makecache

sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://idyylogn.mirror.aliyuncs.com"]
}
EOF
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
sudo yum makecache fast
sudo yum -y install docker-ce
# sudo sed -i "16iExecStart=\nExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix://var/run/docker.sock" /usr/lib/systemd/system/docker.service
sudo systemctl daemon-reload
sudo systemctl restart docker