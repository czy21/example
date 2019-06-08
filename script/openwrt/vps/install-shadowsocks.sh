#!/bin/bash

wget --no-check-certificate -O shadowsocks-libev.sh https://raw.githubusercontent.com/teddysun/shadowsocks_install/master/shadowsocks-libev.sh
sudo chmod +x shadowsocks-libev.sh
sudo ./shadowsocks-libev.sh 2>&1 | tee shadowsocks-libev.log

wget --no-check-certificate https://github.com/teddysun/across/raw/master/bbr.sh
sudo chmod +x bbr.sh

sudo ./bbr.sh