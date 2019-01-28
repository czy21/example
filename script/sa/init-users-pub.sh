#!/bin/bash

set -e

for file in $(ls ./pubs/*.pub);
    do 
    rsa_pub="$rsa_pub"command=\"read\",no-X11-forwarding,no-agent-forwarding,no-pty,no-user-rc"`cat $file`\n"
done
echo -e $rsa_pub > temp
./user-tunnel.sh $1 -create < temp
rm -rf temp

for file in $(ls ./pubs/*.pub);
    do 
    rsa_pub="$rsa_pub`cat $file`\n"
done
echo -e $rsa_pub > temp
./user-tunnel.sh $1 -create < temp
rm -rf temp
