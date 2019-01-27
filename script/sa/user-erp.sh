#!/bin/bash

# $1: user@host
# $2: -init | -import
# -init ./user-tunnel.sh user@host -init
# -import ./user-tunnel.sh user@host -import < ~/.ssh/[user]-rsa.pub
set -e
case "$2" in
        -init)
        ssh $1 "sudo useradd -m erp;
                sudo passwd -d erp;
                sudo -u erp bash -c "\""set -e;cd;
                chmod 750 ~;mkdir .ssh;chmod 700 .ssh;
                touch .ssh/authorized_keys;
                chmod 644 .ssh/authorized_keys;"\"
        ;;
        -import)
        ssh $1 "sudo -u erp bash -c" \''set -e;cd;cat >>.ssh/authorized_keys;'\'
        ;;
        *)
        echo -e "\033[40;33m unknow input param \033[0m"
        ;;
esac