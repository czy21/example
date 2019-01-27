#!/bin/bash


# $1: user@host
# $2: -init | -import
# -init ./tunnel.sh cent_a -init
# -import ./tunnel.sh cent_a -import < ~/.ssh/czy-rsa.pub
set -e
case "$2" in
        -init)
        ssh $1 "sudo useradd -m tunnel"
        ssh $1 "sudo -u tunnel bash -c" \''set -e;cd;mkdir .ssh;chmod 700 .ssh;touch .ssh/authorized_keys;chmod 644 .ssh/authorized_keys'\'
        ;;
        -import)
        ssh $1 "sudo -u tunnel bash -c" \''set -e;cd;echo -n "command=\"read\",no-X11-forwarding,no-agent-forwarding,no-pty,no-user-rc " >>.ssh/authorized_keys;cat >>.ssh/authorized_keys;'\'
        ;;
        *)
        echo -e "\033[40;33m unknow input param \033[0m"
        ;;
esac