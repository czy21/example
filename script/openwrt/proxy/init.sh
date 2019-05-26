#!/bin/bash

set -e
# $1: user@host
# $2: ~/.ssh/[private_key]
# example: ./init.sh user@host ~/.ssh/[private_key]
# scp $2 $1:
scp ./polipo/config $1:/etc/polipo/
scp -r ./init.d/* $1:/etc/init.d/
ssh $1 "bash -c" \''chmod 600 ~/softium-bruce'\'
ssh $1 "bash -c" \''/etc/init.d/ssh-socks enable;/etc/init.d/ssh-http enable;reboot;'\'