#!/bin/bash

set -e
sudo bash -c 'echo -e "
[mysql57-community]
name=MySQL 5.7 Community Server
baseurl=http://repo.mysql.com/yum/mysql-5.7-community/el/\$releasever/\$basearch/
enabled=1
gpgcheck=0
" > /etc/yum.repos.d/mysql57.repo'
