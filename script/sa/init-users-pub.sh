#!/bin/bash


for file in $(ls ./pubs/*.pub);
    do 
    rsa_pub="$rsa_pub"command=\"read\",no-X11-forwarding,no-agent-forwarding,no-pty,no-user-rc"`cat $file`\n"
done
echo -e $rsa_pub > temp
./user-tunnel.sh cent_a -create < temp
rm -rf temp