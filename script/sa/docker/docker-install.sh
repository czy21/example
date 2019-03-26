#!/bin/bash

set -e

sudo yum -y install wget

# ali source
# if [! -f "/etc/yum.repos.d/CentOS-Base.repo_bak"];then
#     sudo mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo_bak
# fi
# sudo wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
# sudo yum clean all
# sudo yum makecache
# sudo yum -y update

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
sudo sed -i "16iExecStart=\nExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix://var/run/docker.sock" /usr/lib/systemd/system/docker.service
sudo systemctl daemon-reload
sudo systemctl restart docker

sudo mkdir -p /var/lib/registry/auth
sudo mkdir -p /var/lib/registry/certs

sudo docker run --entrypoint htpasswd registry -Bbn chenzhaoyu chenzhaoyu > /var/lib/registry/auth/htpasswd

# sudo openssl req -newkey rsa:4096 -nodes -sha256 -keyout /var/lib/registry/certs/domain.key -x509 -days 365 -out /var/lib/registry/certs/domain.crt

docker run -d \
  -p 5000:5000 \
  --restart=always \
  --name registry \
  -v /var/lib/registry:/var/lib/registry \
  -v /var/lib/registry/auth:/auth \
  -e "REGISTRY_AUTH=htpasswd" \
  -e "REGISTRY_AUTH_HTPASSWD_REALM=Registry Realm" \
  -e REGISTRY_AUTH_HTPASSWD_PATH=/auth/htpasswd \
  -v /var/lib/registry/certs:/certs \
  -e REGISTRY_HTTP_TLS_CERTIFICATE=/certs/domain.crt \
  -e REGISTRY_HTTP_TLS_KEY=/certs/domain.key \
  registry:2.7
